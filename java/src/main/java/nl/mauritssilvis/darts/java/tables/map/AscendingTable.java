/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.map;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import nl.mauritssilvis.darts.java.checkouts.Checkout;
import nl.mauritssilvis.darts.java.settings.BoardType;
import nl.mauritssilvis.darts.java.settings.CheckType;
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
    private final CheckType checkInType;
    private final CheckType checkoutType;
    private final Map<Integer, List<Checkout>> checkoutMap;

    private AscendingTable(
            BoardType boardType,
            CheckType checkInType,
            CheckType checkoutType,
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
        this.checkInType = checkInType;
        this.checkoutType = checkoutType;
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
     * check-in type, checkout type and checkout map.
     *
     * @param boardType    the dartboard type
     * @param checkInType  the check-in type
     * @param checkoutType the checkout type
     * @param checkoutMap  the checkout map
     * @return a new {@code AscendingTable} with the specified dartboard type,
     * check-in type, checkout type and checkout map.
     * @throws IllegalArgumentException if some checkouts in the checkout map do
     *                                  not have the same score as their
     *                                  checkout map key
     */
    public static Table of(
            BoardType boardType,
            CheckType checkInType,
            CheckType checkoutType,
            Map<Integer, ? extends Collection<? extends Checkout>> checkoutMap
    ) {
        return new AscendingTable(boardType, checkInType, checkoutType, checkoutMap);
    }

    @Override
    public BoardType getBoardType() {
        return boardType;
    }

    @Override
    public CheckType getCheckInType() {
        return checkInType;
    }

    @Override
    public CheckType getCheckoutType() {
        return checkoutType;
    }

    @Override
    public Map<Integer, List<Checkout>> getCheckoutMap() {
        return checkoutMap;
    }
}
