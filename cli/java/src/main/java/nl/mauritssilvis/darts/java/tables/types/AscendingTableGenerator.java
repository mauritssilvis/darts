/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.types;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import nl.mauritssilvis.darts.java.boards.Board;
import nl.mauritssilvis.darts.java.boards.Field;
import nl.mauritssilvis.darts.java.boards.FieldType;
import nl.mauritssilvis.darts.java.boards.types.BoardFactory;
import nl.mauritssilvis.darts.java.finders.checkouts.Checkout;
import nl.mauritssilvis.darts.java.finders.checkouts.CheckoutFinder;
import nl.mauritssilvis.darts.java.finders.checkouts.types.CheckoutFinderFactory;
import nl.mauritssilvis.darts.java.settings.*;
import nl.mauritssilvis.darts.java.tables.Table;
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
final class AscendingTableGenerator implements TableGenerator {
    private final int numThrows;
    private final ThrowMode throwMode;
    private final FinderType finderType;
    @ToString.Include
    private final Settings settings;

    private final List<Field> firstFields;
    private final List<Field> middleFields;
    private final List<Field> lastFields;

    private final Map<Integer, List<List<Field>>> fieldsPerThrowMap = new HashMap<>();
    private final Map<Integer, CheckoutFinder> checkoutFinderMap = new HashMap<>();

    private AscendingTableGenerator(Settings settings) {
        this.settings = settings;

        numThrows = settings.getNumThrows();
        throwMode = settings.getThrowMode();
        finderType = settings.getFinderType();

        BoardType boardType = settings.getBoardType();
        CheckMode checkInMode = settings.getCheckInMode();
        CheckMode checkoutMode = settings.getCheckoutMode();

        Board board = BoardFactory.create(boardType);

        firstFields = getFields(board, checkInMode);
        middleFields = getFields(board, CheckMode.ANY);
        lastFields = getFields(board, checkoutMode);
    }

    /**
     * Returns a new {@code AscendingTableGenerator} with the specified
     * settings.
     *
     * @param settings the table settings
     * @return a new {@code AscendingTableGenerator} with the specified
     * settings
     */
    public static TableGenerator of(Settings settings) {
        return new AscendingTableGenerator(settings);
    }

    @Override
    public Table generate(int minScore, int maxScore) {
        Map<Integer, List<Checkout>> checkoutMap;

        if (maxScore < minScore) {
            checkoutMap = Collections.emptyMap();
        } else if (!settings.hasFixedNumThrows()) {
            checkoutMap = getOptimalCheckouts(minScore, maxScore);
        } else {
            checkoutMap = switch (throwMode) {
                case OPTIMAL -> getOptimalFixedThrowCheckouts(minScore, maxScore);
                case FIXED -> getAllFixedThrowCheckouts(minScore, maxScore);
            };
        }

        return AscendingTable.of(settings, checkoutMap);
    }

    private static List<Field> getFields(Board board, CheckMode checkMode) {
        List<Field> fields = new ArrayList<>();

        if (checkMode == CheckMode.ANY) {
            fields.addAll(board.getFields(FieldType.SINGLE));
        }

        fields.addAll(board.getFields(FieldType.DOUBLE));

        if (checkMode != CheckMode.DOUBLE) {
            fields.addAll(board.getFields(FieldType.TRIPLE));
        }

        if (checkMode == CheckMode.ANY) {
            fields.addAll(board.getFields(FieldType.QUADRUPLE));
        }

        return fields;
    }

    private Map<Integer, List<Checkout>> getOptimalCheckouts(int minScore, int maxScore) {
        Map<Integer, List<Checkout>> checkoutMap = new HashMap<>();

        for (int i = minScore; i <= maxScore; i++) {
            int throwCount = 1;

            List<Checkout> checkouts = Collections.emptyList();

            while (true) {
                Collection<List<Field>> fieldsPerThrow = getFieldsPerThrow(throwCount);

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

    private Map<Integer, List<Checkout>> getOptimalFixedThrowCheckouts(int minScore, int maxScore) {
        Map<Integer, List<Checkout>> checkoutMap = new HashMap<>();

        for (int i = minScore; i <= maxScore; i++) {
            List<Checkout> checkouts = Collections.emptyList();

            for (int j = 1; j < numThrows; j++) {
                Collection<List<Field>> fieldsPerThrow = getFieldsPerThrow(j);

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

            Collection<List<Field>> fieldsPerThrow = getFieldsPerThrow(numThrows);
            CheckoutFinder checkoutFinder = getCheckoutFinder(fieldsPerThrow);

            checkouts = checkoutFinder.find(i);
            checkoutMap.put(i, checkouts);
        }

        return checkoutMap;
    }

    private Map<Integer, List<Checkout>> getAllFixedThrowCheckouts(int minScore, int maxScore) {
        Map<Integer, List<Checkout>> checkoutMap = new HashMap<>();

        for (int i = minScore; i <= maxScore; i++) {
            Collection<List<Field>> fieldsPerThrow = getFieldsPerThrow(numThrows);
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
