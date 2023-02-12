/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.ascending;

import lombok.ToString;
import nl.mauritssilvis.darts.java.checkouts.Checkout;
import nl.mauritssilvis.darts.java.settings.BoardType;
import nl.mauritssilvis.darts.java.settings.CheckMode;
import nl.mauritssilvis.darts.java.settings.ThrowMode;
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
    private CheckMode checkInMode = CheckMode.ANY;
    private CheckMode checkoutMode = CheckMode.DOUBLE;
    private int numThrows = -1;
    private ThrowMode throwMode = ThrowMode.OPTIMAL;
    private final Map<Integer, List<Checkout>> checkoutMap = new HashMap<>();

    private AscendingTableBuilder() {
    }

    /**
     * Returns a new {@code AscendingTableBuilder} with default values for the
     * dartboard type, check-in and checkout modes.
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
    public TableBuilder setCheckInMode(CheckMode checkInMode) {
        this.checkInMode = checkInMode;
        return this;
    }

    @Override
    public TableBuilder setNumThrows(int numThrows) {
        this.numThrows = numThrows;
        return this;
    }

    @Override
    public TableBuilder setThrowMode(ThrowMode throwMode) {
        this.throwMode = throwMode;
        return this;
    }

    @Override
    public TableBuilder setCheckoutMode(CheckMode checkoutMode) {
        this.checkoutMode = checkoutMode;
        return this;
    }

    @Override
    public TableBuilder setCheckouts(int score, Collection<? extends Checkout> checkouts) {
        checkoutMap.put(score, List.copyOf(checkouts));
        return this;
    }

    @Override
    public Table build() {
        return AscendingTable.of(boardType, checkInMode, checkoutMode, numThrows, throwMode, checkoutMap);
    }
}
