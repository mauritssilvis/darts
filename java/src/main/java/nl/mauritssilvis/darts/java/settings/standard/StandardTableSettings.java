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
final class StandardTableSettings implements TableSettings {
    private final BoardType boardType;
    private final CheckMode checkInMode;
    private final CheckMode checkoutMode;
    private final int numThrows;
    private final ThrowMode throwMode;
    private final FinderType finderType;
    private final TableType tableType;

    private StandardTableSettings(
            BoardType boardType,
            CheckMode checkInMode,
            CheckMode checkoutMode,
            int numThrows,
            ThrowMode throwMode,
            FinderType finderType,
            TableType tableType
    ) {
        this.boardType = boardType;
        this.checkInMode = checkInMode;
        this.checkoutMode = checkoutMode;
        this.numThrows = numThrows;
        this.throwMode = throwMode;
        this.finderType = finderType;
        this.tableType = tableType;
    }

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
    static TableSettings of(
            BoardType boardType,
            CheckMode checkInMode,
            CheckMode checkoutMode,
            int numThrows,
            ThrowMode throwMode,
            FinderType finderType,
            TableType tableType
    ) {
        return new StandardTableSettings(
                boardType, checkInMode, checkoutMode, numThrows, throwMode, finderType, tableType
        );
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
    public boolean hasFixedNumThrows() {
        return numThrows != -1;
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
    public FinderType getFinderType() {
        return finderType;
    }

    @Override
    public TableType getTableType() {
        return tableType;
    }
}
