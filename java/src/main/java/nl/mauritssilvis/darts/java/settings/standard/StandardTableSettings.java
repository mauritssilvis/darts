/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.settings.standard;

import nl.mauritssilvis.darts.java.settings.*;

/**
 * An implementation of the {@code TableSettings} interface that can store
 * checkout table settings.
 * <p>
 * Relevant design patterns: immutable object, static factory method.
 */
class StandardTableSettings implements TableSettings {
    /**
     * Returns a new {@code StandardTableSettings} object with the specified
     * properties.
     *
     * @param boardType    the dartboard type
     * @param checkInMode  the check-in mode
     * @param checkoutMode the checkout mode
     * @param numThrows    the number of throws
     * @param throwMode    the throw mode
     * @param finderType   the finder type
     * @param tableType    the table type
     */
    StandardTableSettings(
            BoardType boardType,
            CheckMode checkInMode,
            CheckMode checkoutMode,
            int numThrows,
            ThrowMode throwMode,
            FinderType finderType,
            TableType tableType) {
    }

    @Override
    public BoardType getBoardType() {
        return null;
    }

    @Override
    public CheckMode getCheckInMode() {
        return null;
    }

    @Override
    public CheckMode getCheckoutMode() {
        return null;
    }

    @Override
    public boolean hasFixedNumThrows() {
        return false;
    }

    @Override
    public int getNumThrows() {
        return 0;
    }

    @Override
    public ThrowMode getThrowMode() {
        return null;
    }

    @Override
    public FinderType getFinderType() {
        return null;
    }

    @Override
    public TableType getTableType() {
        return null;
    }
}
