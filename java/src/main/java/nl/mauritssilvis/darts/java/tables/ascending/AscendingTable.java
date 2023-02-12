/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.ascending;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import nl.mauritssilvis.darts.java.checkouts.Checkout;
import nl.mauritssilvis.darts.java.settings.BoardType;
import nl.mauritssilvis.darts.java.settings.CheckMode;
import nl.mauritssilvis.darts.java.settings.ThrowMode;
import nl.mauritssilvis.darts.java.tables.Table;

import java.util.*;
import java.util.stream.Collectors;

/**
 * An implementation of the {@code Table} interface that stores checkouts in
 * ascending order.
 * <p>
 * Relevant design patterns: immutable object, static factory method.
 */
@EqualsAndHashCode
@ToString
public final class AscendingTable implements Table {
    private final BoardType boardType;
    private final CheckMode checkInMode;
    private final CheckMode checkoutMode;
    private final int numThrows;
    private final ThrowMode throwMode;
    private final Map<Integer, List<Checkout>> checkoutMap;

    private AscendingTable(
            BoardType boardType,
            CheckMode checkInMode,
            CheckMode checkoutMode,
            int numThrows,
            ThrowMode throwMode,
            Map<Integer, ? extends Collection<? extends Checkout>> checkoutMap
    ) {
        boolean hasOtherScore = checkoutMap.entrySet().stream()
                .anyMatch(e -> e.getValue().stream()
                        .anyMatch(checkout -> checkout.getScore() != e.getKey())
                );

        if (hasOtherScore) {
            throw new IllegalArgumentException(
                    "Checkouts should have the same score as their checkout map key"
            );
        }

        this.boardType = boardType;
        this.checkInMode = checkInMode;
        this.checkoutMode = checkoutMode;
        this.numThrows = numThrows;
        this.throwMode = throwMode;
        this.checkoutMap = Collections.unmodifiableMap(
                checkoutMap.entrySet().stream()
                        .collect(
                                Collectors.toMap(
                                        Map.Entry::getKey, e -> List.copyOf(e.getValue()), (e1, e2) -> e1, TreeMap::new
                                )
                        )
        );
    }

    /**
     * Returns a new {@code AscendingTable} with the specified dartboard type,
     * check-in mode, checkout mode and checkout map.
     *
     * @param boardType    the dartboard type
     * @param checkInMode  the check-in mode
     * @param checkoutMode the checkout mode
     * @param numThrows    the number of throws if fixed and -1 otherwise
     * @param throwMode    the throw mode
     * @param checkoutMap  the checkout map
     * @return a new {@code AscendingTable} with the specified dartboard type,
     * check-in mode, checkout mode and checkout map.
     * @throws IllegalArgumentException if some checkouts in the checkout map do
     *                                  not have the same score as their
     *                                  checkout map key
     */
    public static Table of(
            BoardType boardType,
            CheckMode checkInMode,
            CheckMode checkoutMode,
            int numThrows,
            ThrowMode throwMode,
            Map<Integer, ? extends Collection<? extends Checkout>> checkoutMap
    ) {
        return new AscendingTable(boardType, checkInMode, checkoutMode, numThrows, throwMode, checkoutMap);
    }

    @Override
    public BoardType getBoardType() {
        return boardType;
    }

    @Override
    public CheckMode getCheckInMode() {
        return checkInMode;
    }

    @Override
    public CheckMode getCheckoutMode() {
        return checkoutMode;
    }

    @Override
    public int getNumThrows() {
        return numThrows;
    }

    @Override
    public ThrowMode getThrowMode() {
        return throwMode;
    }

    @Override
    public Map<Integer, List<Checkout>> getCheckoutMap() {
        return checkoutMap;
    }
}
