/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.map;

import lombok.ToString;
import nl.mauritssilvis.darts.java.checkouts.Checkout;
import nl.mauritssilvis.darts.java.settings.BoardType;
import nl.mauritssilvis.darts.java.settings.CheckType;
import nl.mauritssilvis.darts.java.tables.Table;
import nl.mauritssilvis.darts.java.tables.TableBuilder;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An implementation of the {@code TableBuilder} interface that builds
 * {@code AscendingTable} objects. Default values are provided for unspecified
 * properties.
 * <p>
 * Relevant design patterns: builder, static factory method.
 */
@ToString
public final class AscendingTableBuilder implements TableBuilder {
    private BoardType boardType = BoardType.LONDON;
    private CheckType checkInType = CheckType.ANY;
    private CheckType checkoutType = CheckType.DOUBLE;
    private final Map<Integer, List<Checkout>> checkoutMap = new HashMap<>();

    private AscendingTableBuilder() {
    }

    /**
     * Returns a new {@code AscendingTableBuilder} with default values for the
     * dartboard, check-in and checkout types.
     *
     * @return a new {@code AscendingTableBuilder}
     */
    public static TableBuilder create() {
        return new AscendingTableBuilder();
    }

    @Override
    public TableBuilder setBoardType(BoardType boardType) {
        this.boardType = boardType;
        return this;
    }

    @Override
    public TableBuilder setCheckInType(CheckType checkInType) {
        this.checkInType = checkInType;
        return this;
    }

    @Override
    public TableBuilder setCheckoutType(CheckType checkoutType) {
        this.checkoutType = checkoutType;
        return this;
    }

    @Override
    public TableBuilder setCheckouts(int score, Collection<? extends Checkout> checkouts) {
        checkoutMap.put(score, List.copyOf(checkouts));
        return this;
    }

    @Override
    public Table build() {
        return AscendingTable.of(boardType, checkInType, checkoutType, checkoutMap);
    }
}
