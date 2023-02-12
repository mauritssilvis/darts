/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.ascending;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import nl.mauritssilvis.darts.java.boards.Board;
import nl.mauritssilvis.darts.java.boards.Field;
import nl.mauritssilvis.darts.java.boards.FieldType;
import nl.mauritssilvis.darts.java.boards.factory.BoardFactory;
import nl.mauritssilvis.darts.java.checkouts.Checkout;
import nl.mauritssilvis.darts.java.checkouts.CheckoutFinder;
import nl.mauritssilvis.darts.java.checkouts.factory.CheckoutFinderFactory;
import nl.mauritssilvis.darts.java.settings.BoardType;
import nl.mauritssilvis.darts.java.settings.CheckType;
import nl.mauritssilvis.darts.java.settings.ThrowMode;
import nl.mauritssilvis.darts.java.settings.FinderType;
import nl.mauritssilvis.darts.java.tables.Table;
import nl.mauritssilvis.darts.java.tables.TableBuilder;
import nl.mauritssilvis.darts.java.tables.TableGenerator;

import java.util.*;
import java.util.stream.IntStream;

/**
 * An implementation of the {@code TableGenerator} interface that generates
 * {@code AscendingTable} objects.
 * <p>
 * Relevant design patterns: strategy, lazy initialization, immutable object,
 * static factory method.
 */
@EqualsAndHashCode
@ToString(onlyExplicitlyIncluded = true)
public final class AscendingTableGenerator implements TableGenerator {
    @ToString.Include
    private final BoardType boardType;
    @ToString.Include
    private final CheckType checkInType;
    @ToString.Include
    private final CheckType checkoutType;
    @ToString.Include
    private final int numThrows;
    @ToString.Include
    private final ThrowMode throwMode;
    @ToString.Include
    private final FinderType finderType;

    private final List<Field> firstFields;
    private final List<Field> middleFields;
    private final List<Field> lastFields;

    private final Map<Integer, List<List<Field>>> fieldsPerThrowMap = new HashMap<>();
    private final Map<Integer, CheckoutFinder> checkoutFinderMap = new HashMap<>();

    private AscendingTableGenerator(
            BoardType boardType,
            CheckType checkInType,
            CheckType checkoutType,
            int numThrows,
            ThrowMode throwMode,
            FinderType finderType
    ) {
        this.boardType = boardType;
        this.checkInType = checkInType;
        this.checkoutType = checkoutType;
        this.numThrows = numThrows;
        this.throwMode = throwMode;
        this.finderType = finderType;

        Board board = BoardFactory.create(boardType);
        firstFields = getFields(board, checkInType);
        middleFields = getFields(board, CheckType.ANY);
        lastFields = getFields(board, checkoutType);
    }

    /**
     * Returns a new {@code AscendingTableGenerator} with the specified
     * dartboard, check-in, checkout and finder types.
     *
     * @param boardType    the dartboard type
     * @param checkInType  the check-in type
     * @param checkoutType the checkout type
     * @param numThrows    the number of throws if fixed and -1 otherwise
     * @param throwMode   the finder mode
     * @param finderType   the checkout finder type
     * @return a new {@code AscendingTableGenerator} with the specified
     * dartboard, check-in, checkout and finder types.
     */
    public static TableGenerator of(
            BoardType boardType,
            CheckType checkInType,
            CheckType checkoutType,
            int numThrows,
            ThrowMode throwMode,
            FinderType finderType
    ) {
        return new AscendingTableGenerator(boardType, checkInType, checkoutType, numThrows, throwMode, finderType);
    }

    @Override
    public Table generate(int minScore, int maxScore) {
        Map<Integer, List<Checkout>> checkoutMap;

        if (numThrows == -1) {
            checkoutMap = getRegularCheckoutMap(minScore, maxScore);
        } else if (throwMode == ThrowMode.OPTIMAL) {
            checkoutMap = getMinimumThrowCheckoutMap(minScore, maxScore);
        } else {
            checkoutMap = getFixedThrowCheckoutMap(minScore, maxScore);
        }

        TableBuilder tableBuilder = AscendingTableBuilder.create()
                .setBoardType(boardType)
                .setCheckInType(checkInType)
                .setCheckoutType(checkoutType);

        checkoutMap.forEach(tableBuilder::setCheckouts);

        return tableBuilder.build();
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

    private Map<Integer, List<Checkout>> getRegularCheckoutMap(int minScore, int maxScore) {
        Map<Integer, List<Checkout>> checkoutMap = new HashMap<>();

        for (int i = minScore; i <= maxScore; i++) {
            int throwCount = 1;

            List<Checkout> checkouts = Collections.emptyList();

            while (true) {
                List<List<Field>> fieldsPerThrow = getFieldsPerThrow(throwCount);

                if (getMinScore(fieldsPerThrow) > i) {
                    break;
                } else if (getMaxScore(fieldsPerThrow) < i) {
                    throwCount++;
                    continue;
                }

                CheckoutFinder checkoutFinder = getCheckoutFinder(fieldsPerThrow);
                checkouts = checkoutFinder.find(i);

                if (!checkouts.isEmpty()) {
                    break;
                }

                throwCount++;
            }

            checkoutMap.put(i, checkouts);
        }

        return checkoutMap;
    }

    private Map<Integer, List<Checkout>> getMinimumThrowCheckoutMap(int minScore, int maxScore) {
        Map<Integer, List<Checkout>> checkoutMap = new HashMap<>();

        for (int i = minScore; i <= maxScore; i++) {
            List<Checkout> checkouts = Collections.emptyList();

            for (int j = 1; j < numThrows; j++) {
                List<List<Field>> fieldsPerThrow = getFieldsPerThrow(j);

                if (getMinScore(fieldsPerThrow) > i) {
                    break;
                } else if (getMaxScore(fieldsPerThrow) < i) {
                    continue;
                }

                CheckoutFinder checkoutFinder = getCheckoutFinder(fieldsPerThrow);
                checkouts = checkoutFinder.find(i);

                if (!checkouts.isEmpty()) {
                    break;
                }
            }

            if (!checkouts.isEmpty()) {
                checkoutMap.put(i, Collections.emptyList());
                continue;
            }

            List<List<Field>> fieldsPerThrow = getFieldsPerThrow(numThrows);
            CheckoutFinder checkoutFinder = getCheckoutFinder(fieldsPerThrow);

            checkouts = checkoutFinder.find(i);
            checkoutMap.put(i, checkouts);
        }

        return checkoutMap;
    }

    private Map<Integer, List<Checkout>> getFixedThrowCheckoutMap(int minScore, int maxScore) {
        Map<Integer, List<Checkout>> checkoutMap = new HashMap<>();

        for (int i = minScore; i <= maxScore; i++) {
            List<List<Field>> fieldsPerThrow = getFieldsPerThrow(numThrows);
            CheckoutFinder checkoutFinder = getCheckoutFinder(fieldsPerThrow);

            List<Checkout> checkouts = checkoutFinder.find(i);
            checkoutMap.put(i, checkouts);
        }

        return checkoutMap;
    }

    private List<List<Field>> getFieldsPerThrow(int throwCount) {
        if (fieldsPerThrowMap.containsKey(throwCount)) {
            return fieldsPerThrowMap.get(throwCount);
        }

        if (throwCount == 1) {
            List<Field> fields = new ArrayList<>(firstFields);
            fields.retainAll(lastFields);

            List<List<Field>> fieldsPerThrow = Collections.singletonList(fields);
            fieldsPerThrowMap.put(1, fieldsPerThrow);

            return fieldsPerThrow;
        }

        List<List<Field>> fieldsPerThrow = new ArrayList<>();

        fieldsPerThrow.add(firstFields);

        IntStream.range(0, throwCount - 2)
                .forEach(i -> fieldsPerThrow.add(middleFields));

        fieldsPerThrow.add(lastFields);

        fieldsPerThrowMap.put(throwCount, fieldsPerThrow);

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
        int throwCount = fieldsPerThrow.size();

        if (checkoutFinderMap.containsKey(throwCount)) {
            return checkoutFinderMap.get(throwCount);
        }

        CheckoutFinder checkoutFinder = CheckoutFinderFactory.create(finderType, fieldsPerThrow);
        checkoutFinderMap.put(throwCount, checkoutFinder);

        return checkoutFinder;
    }
}
