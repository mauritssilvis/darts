/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.settings.tables;

import lombok.ToString;
import nl.mauritssilvis.darts.java.settings.*;

/**
 * An implementation of the {@code SettingsBuilder} interface that builds
 * {@code TableSettings} objects. Default values are provided for
 * unspecified properties.
 * <p>
 * Relevant design patterns: builder, static factory method.
 */
@ToString
public final class TableSettingsBuilder implements SettingsBuilder {
    BoardType boardType = BoardType.LONDON;
    CheckMode checkInMode = CheckMode.ANY;
    CheckMode checkoutMode = CheckMode.DOUBLE;
    int numThrows = -1;
    ThrowMode throwMode = ThrowMode.OPTIMAL;
    FinderType finderType = FinderType.DESCENDING;
    TableType tableType = TableType.ASCENDING;

    private TableSettingsBuilder() {
    }

    /**
     * Returns a new {@code TableSettingsBuilder} with default checkout table
     * settings.
     *
     * @return a new {@code TableSettingsBuilder}
     */
    public static SettingsBuilder create() {
        return new TableSettingsBuilder();
    }

    @Override
    public SettingsBuilder setBoardType(BoardType boardType) {
        this.boardType = boardType;
        return this;
    }

    @Override
    public SettingsBuilder setCheckInMode(CheckMode checkInMode) {
        this.checkInMode = checkInMode;
        return this;
    }

    @Override
    public SettingsBuilder setCheckoutMode(CheckMode checkoutMode) {
        this.checkoutMode = checkoutMode;
        return this;
    }

    @Override
    public SettingsBuilder setNumThrows(int numThrows) {
        this.numThrows = numThrows;
        return this;
    }

    @Override
    public SettingsBuilder setThrowMode(ThrowMode throwMode) {
        this.throwMode = throwMode;
        return this;
    }

    @Override
    public SettingsBuilder setFinderType(FinderType finderType) {
        this.finderType = finderType;
        return this;
    }

    @Override
    public SettingsBuilder setTableType(TableType tableType) {
        this.tableType = tableType;
        return this;
    }

    @Override
    public Settings build() {
        return TableSettings.of(
                boardType, checkInMode, checkoutMode, numThrows, throwMode, finderType, tableType
        );
    }
}
