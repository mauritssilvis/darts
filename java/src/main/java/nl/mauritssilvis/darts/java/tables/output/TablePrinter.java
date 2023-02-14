/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.output;

import nl.mauritssilvis.darts.java.boards.Field;
import nl.mauritssilvis.darts.java.finders.checkouts.Checkout;
import nl.mauritssilvis.darts.java.finders.checkouts.Throw;
import nl.mauritssilvis.darts.java.tables.Table;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * An abstract class that defines an algorithm for getting string
 * representations of tables.
 * <p>
 * Relevant design patterns: template method.
 */
abstract class TablePrinter {
    private final Map<Integer, List<Checkout>> checkoutMap;
    private final Map<Integer, Long> multiplicityMap;
    private final int scoreWidth;
    private final int numCheckouts;
    private final int numThrows;
    private final int throwSize;
    private final int fieldWidth;
    private final int multiplicityWidth;

    TablePrinter(Table table) {
        checkoutMap = table.getCheckoutMap();

        multiplicityMap = getMultiplicityMap(checkoutMap);

        Collection<Checkout> checkouts = checkoutMap.values().stream()
                .flatMap(Collection::stream)
                .toList();

        scoreWidth = getMaxScoreWidth(checkoutMap.keySet());
        numCheckouts = getMaxNumCheckouts(checkoutMap.values());

        if (table.getSettings().hasFixedNumThrows()) {
            numThrows = table.getSettings().getNumThrows();
            throwSize = Math.max(getMaxThrowSize(checkouts), 1);
            fieldWidth = Math.max(getMaxFieldWidth(checkouts), String.valueOf(numThrows).length());
        } else {
            numThrows = getMaxNumThrows(checkouts);
            throwSize = getMaxThrowSize(checkouts);
            fieldWidth = getMaxFieldWidth(checkouts);
        }

        multiplicityWidth = getMaxMultiplicityWidth(multiplicityMap.values());
    }

    int getScoreWidth() {
        return scoreWidth;
    }

    int getNumCheckouts() {
        return numCheckouts;
    }

    int getNumThrows() {
        return numThrows;
    }

    int getThrowSize() {
        return throwSize;
    }

    int getFieldWidth() {
        return fieldWidth;
    }

    int getMultiplicityWidth() {
        return multiplicityWidth;
    }

    String print() {
        startTable();
        processScores();
        endTable();

        return getString();
    }

    private void processScores() {
        List<Integer> scores = checkoutMap.keySet().stream().toList();

        for (int i = 0; i < scores.size(); i++) {
            int score = scores.get(i);
            processScore(score);

            if (i != scores.size() - 1) {
                separateScore();
            }
        }
    }

    private void processScore(int score) {
        startScore(score, checkoutMap.get(score).size());

        startMultiplicity();
        addMultiplicity(multiplicityMap.get(score));
        endMultiplicity();

        processCheckouts(checkoutMap.get(score));

        endScore();
    }

    private void processCheckouts(List<? extends Checkout> checkouts) {
        startCheckouts();

        for (int i = 0; i < checkouts.size(); i++) {
            Checkout checkout = checkouts.get(i);
            processCheckout(checkout);

            if (i != checkouts.size() - 1) {
                separateCheckout();
            }
        }

        endCheckouts();
    }

    private void processCheckout(Checkout checkout) {
        startCheckout();

        startCheckoutScore();
        addCheckoutScore(checkout.getScore());
        endCheckoutScore();

        processThrows(checkout.getThrows());

        processCheckoutMultiplicity(checkout.getMultiplicity());

        endCheckout();
    }

    private void processThrows(List<? extends Throw> throwList) {
        startThrows();

        for (int i = 0; i < throwList.size(); i++) {
            Throw compoundThrow = throwList.get(i);

            processThrow(compoundThrow);

            if (i != throwList.size() - 1) {
                separateThrow();
            }
        }

        IntStream.range(0, numThrows - throwList.size())
                .forEach(i -> addEmptyThrowAfter());

        endThrows();
    }

    private void processThrow(Throw compoundThrow) {
        startThrow();
        processFields(compoundThrow.getFields());
        endThrow();
    }

    private void processFields(List<? extends Field> fields) {
        IntStream.range(0, throwSize - fields.size())
                .forEach(i -> addEmptyFieldBefore());

        for (int i = 0; i < fields.size(); i++) {
            Field field = fields.get(i);

            processField(field);

            if (i != fields.size() - 1) {
                separateField();
            }
        }
    }

    private void processField(Field field) {
        startField();
        addField(field.getName());
        endField();
    }

    private void processCheckoutMultiplicity(long multiplicity) {
        startCheckoutMultiplicity();
        addCheckoutMultiplicity(multiplicity);
        endCheckoutMultiplicity();
    }

    abstract void startTable();

    abstract void endTable();

    abstract void startScore(int score, int numCheckouts);

    abstract void endScore();

    abstract void separateScore();

    abstract void startCheckouts();

    abstract void endCheckouts();

    abstract void startCheckout();

    abstract void endCheckout();

    abstract void separateCheckout();

    abstract void startCheckoutScore();

    abstract void addCheckoutScore(int score);

    abstract void endCheckoutScore();

    abstract void startThrows();

    abstract void endThrows();

    abstract void startThrow();

    abstract void endThrow();

    abstract void separateThrow();

    abstract void addEmptyThrowAfter();

    abstract void addEmptyFieldBefore();

    abstract void startField();

    abstract void addField(String name);

    abstract void endField();

    abstract void separateField();

    abstract void startCheckoutMultiplicity();

    abstract void addCheckoutMultiplicity(long multiplicity);

    abstract void endCheckoutMultiplicity();

    abstract void startMultiplicity();

    abstract void addMultiplicity(long multiplicity);

    abstract void endMultiplicity();

    abstract String getString();

    private static Map<Integer, Long> getMultiplicityMap(
            Map<Integer, ? extends Collection<? extends Checkout>> checkoutMap
    ) {
        return checkoutMap.entrySet().stream()
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                e -> e.getValue().stream()
                                        .mapToLong(Checkout::getMultiplicity)
                                        .sum()
                        )
                );
    }

    private static int getMaxScoreWidth(Collection<Integer> scores) {
        return scores.stream()
                .map(String::valueOf)
                .mapToInt(String::length)
                .max()
                .orElse(0);
    }

    private static int getMaxNumCheckouts(Collection<? extends Collection<? extends Checkout>> checkoutsPerScore) {
        return checkoutsPerScore.stream()
                .mapToInt(Collection::size)
                .max()
                .orElse(0);
    }

    private static int getMaxNumThrows(Collection<? extends Checkout> checkouts) {
        return checkouts.stream()
                .map(Checkout::getThrows)
                .mapToInt(Collection::size)
                .max()
                .orElse(0);
    }

    private static int getMaxThrowSize(Collection<? extends Checkout> checkouts) {
        return checkouts.stream()
                .map(Checkout::getThrows)
                .flatMap(Collection::stream)
                .map(Throw::getFields)
                .mapToInt(Collection::size)
                .max()
                .orElse(0);
    }

    private static int getMaxFieldWidth(Collection<? extends Checkout> checkouts) {
        return checkouts.stream()
                .map(Checkout::getThrows)
                .flatMap(Collection::stream)
                .map(Throw::getFields)
                .flatMap(Collection::stream)
                .map(Field::getName)
                .mapToInt(String::length)
                .max()
                .orElse(0);
    }

    private static int getMaxMultiplicityWidth(Collection<Long> multiplicities) {
        return multiplicities.stream()
                .map(String::valueOf)
                .mapToInt(String::length)
                .max()
                .orElse(0);
    }
}
