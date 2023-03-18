/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.output;

import nl.mauritssilvis.darts.java.api.boards.Field;
import nl.mauritssilvis.darts.java.api.finders.checkouts.Checkout;
import nl.mauritssilvis.darts.java.api.finders.checkouts.Throw;
import nl.mauritssilvis.darts.java.api.settings.Settings;
import nl.mauritssilvis.darts.java.api.tables.Table;

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
 *
 * @author Maurits Silvis
 * @since 0.1.0
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

    /**
     * Creates a new {@code TablePrinter} for the specified checkout table.
     *
     * @param table a checkout table
     */
    TablePrinter(Table table) {
        checkoutMap = table.getCheckoutMap();

        multiplicityMap = getMultiplicityMap(checkoutMap);

        Collection<Checkout> checkouts = checkoutMap.values().stream()
                .flatMap(Collection::stream)
                .toList();

        scoreWidth = getMaxScoreWidth(checkoutMap.keySet());
        numCheckouts = getMaxNumCheckouts(checkoutMap.values());

        Settings settings = table.getSettings();

        if (settings.hasFixedNumThrows()) {
            numThrows = settings.getNumThrows();
            throwSize = Math.max(getMaxThrowSize(checkouts), 1);
            fieldWidth = Math.max(getMaxFieldWidth(checkouts), String.valueOf(numThrows).length());
        } else {
            numThrows = getMaxNumThrows(checkouts);
            throwSize = getMaxThrowSize(checkouts);
            fieldWidth = getMaxFieldWidth(checkouts);
        }

        multiplicityWidth = getMaxMultiplicityWidth(multiplicityMap.values());
    }

    /**
     * Gets the maximum score character width.
     * <p>
     * This property can be used for formatting purposes.
     *
     * @return the maximum score character width
     */
    int getScoreWidth() {
        return scoreWidth;
    }

    /**
     * Gets the maximum number of checkouts per score.
     * <p>
     * This property can be used for formatting purposes.
     *
     * @return the maximum number of checkouts per score
     */
    int getNumCheckouts() {
        return numCheckouts;
    }

    /**
     * Gets the maximum number of throws per checkout.
     * <p>
     * This property can be used for formatting purposes.
     *
     * @return the maximum number of throws per checkout
     */
    int getNumThrows() {
        return numThrows;
    }

    /**
     * Gets the maximum number of fields per throw.
     * <p>
     * This property can be used for formatting purposes.
     *
     * @return the maximum number of fields per throw
     */
    int getThrowSize() {
        return throwSize;
    }

    /**
     * Gets the maximum field character width.
     * <p>
     * This property can be used for formatting purposes.
     *
     * @return the maximum field character width
     */
    int getFieldWidth() {
        return fieldWidth;
    }

    /**
     * Gets the maximum multiplicity character width.
     * <p>
     * This property can be used for formatting purposes.
     *
     * @return the maximum multiplicity character width
     */
    int getMultiplicityWidth() {
        return multiplicityWidth;
    }

    /**
     * Returns a string representation of the stored checkout table.
     * <p>
     * This method directly and indirectly relies on abstract template methods
     * that have to be implemented by child classes.
     *
     * @return a string representation of the stored checkout table
     */
    String print() {
        startTable();
        processScores();
        endTable();

        return getString();
    }

    /**
     * Starts the string representation of the stored checkout table.
     */
    abstract void startTable();

    /**
     * Ends the string representation of the stored checkout table.
     */
    abstract void endTable();

    /**
     * Starts the string representation of a score.
     *
     * @param score        the score
     * @param numCheckouts the number of checkouts for this score
     */
    abstract void startScore(int score, int numCheckouts);

    /**
     * Ends the string representation of a score.
     */
    abstract void endScore();

    /**
     * Separates the string representations of different scores.
     */
    abstract void separateScore();

    /**
     * Starts the string representation of a collection of checkouts.
     */
    abstract void startCheckouts();

    /**
     * Ends the string representation of a collection of checkouts.
     */
    abstract void endCheckouts();

    /**
     * Starts the string representation of a checkout.
     */
    abstract void startCheckout();

    /**
     * Ends the string representation of a checkout.
     */
    abstract void endCheckout();

    /**
     * Separates the string representations of different checkouts.
     */
    abstract void separateCheckout();

    /**
     * Starts the string representation of a checkout score.
     */
    abstract void startCheckoutScore();

    /**
     * Adds the string representation of a checkout score.
     *
     * @param score the checkout score
     */
    abstract void addCheckoutScore(int score);

    /**
     * Ends the string representation of a checkout score.
     */
    abstract void endCheckoutScore();

    /**
     * Starts the string representation of a collection of throws.
     */
    abstract void startThrows();

    /**
     * Ends the string representation of a collection of throws.
     */
    abstract void endThrows();

    /**
     * Starts the string representation of a throw.
     */
    abstract void startThrow();

    /**
     * Ends the string representation of a throw.
     */
    abstract void endThrow();

    /**
     * Separates the string representations of different throws.
     */
    abstract void separateThrow();

    /**
     * Adds the string representation of a missing throw after adding present
     * throws.
     */
    abstract void addEmptyThrowAfter();

    /**
     * Adds the string representation of a missing field before adding present
     * fields.
     */
    abstract void addEmptyFieldBefore();

    /**
     * Starts the string representation of a field.
     */
    abstract void startField();

    /**
     * Adds the string representation of a field.
     *
     * @param name the field name
     */
    abstract void addField(String name);

    /**
     * Ends the string representation of a field.
     */
    abstract void endField();

    /**
     * Separates the string representations of different fields.
     */
    abstract void separateField();

    /**
     * Starts the string representation of a checkout multiplicity.
     */
    abstract void startCheckoutMultiplicity();

    /**
     * Adds the string representation of a checkout multiplicity.
     *
     * @param multiplicity the checkout multiplicity
     */
    abstract void addCheckoutMultiplicity(long multiplicity);

    /**
     * Ends the string representation of a checkout multiplicity.
     */
    abstract void endCheckoutMultiplicity();

    /**
     * Starts the string representation of the total checkout multiplicity for a
     * score.
     */
    abstract void startMultiplicity();

    /**
     * Adds the string representation of the total checkout multiplicity for a
     * score.
     *
     * @param multiplicity the total multiplicity
     */
    abstract void addMultiplicity(long multiplicity);

    /**
     * Ends the string representation of the total checkout multiplicity for a
     * score.
     */
    abstract void endMultiplicity();

    /**
     * Gets the current string representation of the stored checkout table.
     *
     * @return the current string representation of the stored checkout table
     */
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
}
