/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.map;

import lombok.EqualsAndHashCode;
import nl.mauritssilvis.darts.java.boards.Board;
import nl.mauritssilvis.darts.java.boards.Field;
import nl.mauritssilvis.darts.java.boards.FieldType;
import nl.mauritssilvis.darts.java.boards.factory.BoardFactory;
import nl.mauritssilvis.darts.java.checkouts.Checkout;
import nl.mauritssilvis.darts.java.checkouts.CheckoutFinder;
import nl.mauritssilvis.darts.java.checkouts.factory.CheckoutFinderFactory;
import nl.mauritssilvis.darts.java.settings.BoardType;
import nl.mauritssilvis.darts.java.settings.CheckType;
import nl.mauritssilvis.darts.java.settings.FinderType;
import nl.mauritssilvis.darts.java.tables.CheckoutTable;
import nl.mauritssilvis.darts.java.tables.CheckoutTableBuilder;
import nl.mauritssilvis.darts.java.tables.CheckoutTableGenerator;

import java.util.*;
import java.util.stream.IntStream;

/**
 * An implementation of the {@code CheckoutTableGenerator} interface that
 * generates {@code MapBasedCheckoutTable} objects.
 * <p>
 * Relevant design patterns: strategy, lazy initialization, immutable object,
 * static factory method.
 */
@EqualsAndHashCode
public final class MapBasedCheckoutTableGenerator implements CheckoutTableGenerator {
    private final BoardType boardType;
    private final CheckType checkInType;
    private final CheckType checkoutType;
    private final FinderType finderType;

    private final List<Field> firstFields;
    private final List<Field> middleFields;
    private final List<Field> lastFields;

    private final Map<Integer, List<List<Field>>> fieldsPerThrowMap = new HashMap<>();
    private final Map<Integer, CheckoutFinder> checkoutFinderMap = new HashMap<>();

    private MapBasedCheckoutTableGenerator(
            BoardType boardType,
            CheckType checkInType,
            CheckType checkoutType,
            FinderType finderType
    ) {
        this.boardType = boardType;
        this.checkInType = checkInType;
        this.checkoutType = checkoutType;
        this.finderType = finderType;

        Board board = BoardFactory.create(boardType);
        firstFields = getFields(board, checkInType);
        middleFields = getFields(board, CheckType.ANY);
        lastFields = getFields(board, checkoutType);
    }

    /**
     * Returns a new {@code MapBasedCheckoutTableGenerator} with the specified
     * dartboard, check-in and checkout types.
     *
     * @param boardType    the dartboard type
     * @param checkInType  the check-in type
     * @param checkoutType the checkout type
     * @param finderType   the checkout finder type
     * @return a new {@code MapBasedCheckoutTableGenerator} with the specified
     * dartboard, check-in and checkout types.
     */
    public static CheckoutTableGenerator of(
            BoardType boardType,
            CheckType checkInType,
            CheckType checkoutType,
            FinderType finderType
    ) {
        return new MapBasedCheckoutTableGenerator(boardType, checkInType, checkoutType, finderType);
    }

    @Override
    public CheckoutTable generate(int minScore, int maxScore) {
        Map<Integer, List<Checkout>> checkoutMap = getCheckoutMap(minScore, maxScore);

        CheckoutTableBuilder checkoutTableBuilder = MapBasedCheckoutTableBuilder.create()
                .setBoardType(boardType)
                .setCheckInType(checkInType)
                .setCheckoutType(checkoutType);

        checkoutMap.forEach(checkoutTableBuilder::setCheckouts);

        return checkoutTableBuilder.build();
    }

    private static List<Field> getFields(Board board, CheckType checkType) {
        List<Field> fields = new ArrayList<>();

        if (checkType == CheckType.ANY) {
            fields.addAll(board.getFields(FieldType.SINGLE));
        }

        fields.addAll(board.getFields(FieldType.DOUBLE));

        if (checkType != CheckType.DOUBLE) {
            fields.addAll(board.getFields(FieldType.TRIPLE));
        }

        if (checkType == CheckType.ANY) {
            fields.addAll(board.getFields(FieldType.QUADRUPLE));
        }

        return fields;
    }

    private Map<Integer, List<Checkout>> getCheckoutMap(int minScore, int maxScore) {
        Map<Integer, List<Checkout>> checkoutMap = new HashMap<>();

        for (int i = minScore; i <= maxScore; i++) {
            int numThrows = 1;

            List<Checkout> checkouts = Collections.emptyList();

            while (true) {
                List<List<Field>> fieldsPerThrow = getFieldsPerThrow(numThrows);

                if (getMinScore(fieldsPerThrow) > i) {
                    break;
                } else if (getMaxScore(fieldsPerThrow) < i) {
                    numThrows++;
                    continue;
                }

                CheckoutFinder checkoutFinder = getCheckoutFinder(fieldsPerThrow);
                checkouts = checkoutFinder.find(i);

                if (!checkouts.isEmpty()) {
                    break;
                }

                numThrows++;
            }

            checkoutMap.put(i, checkouts);
        }

        return checkoutMap;
    }

    private List<List<Field>> getFieldsPerThrow(int numThrows) {
        if (fieldsPerThrowMap.containsKey(numThrows)) {
            return fieldsPerThrowMap.get(numThrows);
        }

        if (numThrows == 1) {
            List<Field> fields = new ArrayList<>(firstFields);
            fields.retainAll(lastFields);

            List<List<Field>> fieldsPerThrow = Collections.singletonList(fields);
            fieldsPerThrowMap.put(1, fieldsPerThrow);

            return fieldsPerThrow;
        }

        List<List<Field>> fieldsPerThrow = new ArrayList<>();

        fieldsPerThrow.add(firstFields);

        IntStream.range(0, numThrows - 2)
                .forEach(i -> fieldsPerThrow.add(middleFields));

        fieldsPerThrow.add(lastFields);

        fieldsPerThrowMap.put(numThrows, fieldsPerThrow);

        return fieldsPerThrow;
    }

    private static int getMinScore(Collection<? extends Collection<? extends Field>> fieldsPerThrow) {
        return fieldsPerThrow.stream()
                .mapToInt(fields -> fields.stream()
                        .mapToInt(Field::getScore).min().orElse(Integer.MIN_VALUE)
                )
                .sum();
    }

    private static int getMaxScore(Collection<? extends Collection<? extends Field>> fieldsPerThrow) {
        return fieldsPerThrow.stream()
                .mapToInt(fields -> fields.stream()
                        .mapToInt(Field::getScore).max().orElse(Integer.MAX_VALUE)
                )
                .sum();
    }

    private CheckoutFinder getCheckoutFinder(Collection<? extends Collection<? extends Field>> fieldsPerThrow) {
        int numThrows = fieldsPerThrow.size();

        if (checkoutFinderMap.containsKey(numThrows)) {
            return checkoutFinderMap.get(numThrows);
        }

        CheckoutFinder checkoutFinder = CheckoutFinderFactory.create(finderType, fieldsPerThrow);
        checkoutFinderMap.put(numThrows, checkoutFinder);

        return checkoutFinder;
    }
}
