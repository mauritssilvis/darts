/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.settings.standard;

import lombok.ToString;
import nl.mauritssilvis.darts.java.settings.*;

/**
 * An implementation of the {@code TableSettingsBuilder} interface that builds
 * {@code BasicTableSettings} objects. Default values are provided for
 * unspecified properties.
 * <p>
 * Relevant design patterns: builder, static factory method.
 */
@ToString
public class BasicTableSettingsBuilder implements TableSettingsBuilder {
    BoardType boardType = BoardType.LONDON;
    CheckMode checkInMode = CheckMode.ANY;
    CheckMode checkoutMode = CheckMode.DOUBLE;
    int numThrows = -1;
    ThrowMode throwMode = ThrowMode.OPTIMAL;
    FinderType finderType = FinderType.DESCENDING;
    TableType tableType = TableType.ASCENDING;

    private BasicTableSettingsBuilder() {
    }

    /**
     * Returns a new {@code BasicTableSettingsBuilder} with default checkout
     * table settings.
     *
     * @return a new {@code BasicTableSettingsBuilder}
     */
    public static TableSettingsBuilder create() {
        return new BasicTableSettingsBuilder();
    }

    @Override
    public TableSettingsBuilder setBoardType(BoardType boardType) {
        this.boardType = boardType;
        return this;
    }

    @Override
    public TableSettingsBuilder setCheckInMode(CheckMode checkInMode) {
        this.checkInMode = checkInMode;
        return this;
    }

    @Override
    public TableSettingsBuilder setCheckoutMode(CheckMode checkoutMode) {
        this.checkoutMode = checkoutMode;
        return this;
    }

    @Override
    public TableSettingsBuilder setNumThrows(int numThrows) {
        this.numThrows = numThrows;
        return this;
    }

    @Override
    public TableSettingsBuilder setThrowMode(ThrowMode throwMode) {
        this.throwMode = throwMode;
        return this;
    }

    @Override
    public TableSettingsBuilder setFinderType(FinderType finderType) {
        this.finderType = finderType;
        return this;
    }

    @Override
    public TableSettingsBuilder setTableType(TableType tableType) {
        this.tableType = tableType;
        return this;
    }

    @Override
    public TableSettings build() {
        return BasicTableSettings.of(
                boardType, checkInMode, checkoutMode, numThrows, throwMode, finderType, tableType
        );
    }
}
