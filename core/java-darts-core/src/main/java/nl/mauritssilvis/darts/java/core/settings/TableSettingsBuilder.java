/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.core.settings;

import lombok.ToString;
import nl.mauritssilvis.darts.java.api.settings.*;

/**
 * An implementation of the {@code SettingsBuilder} interface that builds
 * {@code TableSettings} objects. Default values are provided for
 * unspecified properties.
 * <p>
 * Relevant design patterns: builder, static factory method.
 *
 * @author Maurits Silvis
 * @since 0.5.0
 */
@ToString
public final class TableSettingsBuilder implements SettingsBuilder {
    private BoardType boardType = BoardType.LONDON;
    private CheckMode checkInMode = CheckMode.ANY;
    private CheckMode checkoutMode = CheckMode.DOUBLE;
    private int numThrows;
    private ThrowMode throwMode = ThrowMode.OPTIMAL;
    private FinderType finderType = FinderType.DESCENDING;
    private TableType tableType = TableType.ASCENDING;

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
