/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.settings.standard;

import nl.mauritssilvis.darts.java.settings.*;

/**
 * An implementation of the {@code TableSettingsBuilder} interface that builds
 * {@code BasicTableSettings} objects. Default values are provided for
 * unspecified properties.
 * <p>
 * Relevant design patterns: builder, static factory method.
 */
public class BasicTableSettingsBuilder implements TableSettingsBuilder {
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
        return null;
    }

    @Override
    public TableSettingsBuilder setCheckInMode(CheckMode checkInMode) {
        return null;
    }

    @Override
    public TableSettingsBuilder setCheckoutMode(CheckMode checkoutMode) {
        return null;
    }

    @Override
    public TableSettingsBuilder setNumThrows(int numThrows) {
        return null;
    }

    @Override
    public TableSettingsBuilder setThrowMode(ThrowMode throwMode) {
        return null;
    }

    @Override
    public TableSettingsBuilder setFinderType(FinderType finderType) {
        return null;
    }

    @Override
    public TableSettingsBuilder setTableType(TableType tableType) {
        return null;
    }

    @Override
    public TableSettings build() {
        return null;
    }
}
