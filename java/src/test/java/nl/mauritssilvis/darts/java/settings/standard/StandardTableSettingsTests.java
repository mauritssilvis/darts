/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.settings.standard;

import nl.mauritssilvis.darts.java.settings.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StandardTableSettingsTests {
    @Test
    void getTheBoardType() {
        BoardType boardType = BoardType.QUADRO;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = -1;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        FinderType finderType = FinderType.DESCENDING;
        TableType tableType = TableType.ASCENDING;

        TableSettings tableSettings = new StandardTableSettings(
                boardType, checkInMode, checkoutMode, numThrows, throwMode, finderType, tableType
        );

        Assertions.assertEquals(boardType, tableSettings.getBoardType());
    }

    @Test
    void getTheCheckInMode() {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.MASTER;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = -1;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        FinderType finderType = FinderType.DESCENDING;
        TableType tableType = TableType.ASCENDING;

        TableSettings tableSettings = new StandardTableSettings(
                boardType, checkInMode, checkoutMode, numThrows, throwMode, finderType, tableType
        );

        Assertions.assertEquals(checkInMode, tableSettings.getCheckInMode());
    }

    @Test
    void getTheCheckoutMode() {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.ANY;
        int numThrows = -1;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        FinderType finderType = FinderType.DESCENDING;
        TableType tableType = TableType.ASCENDING;

        TableSettings tableSettings = new StandardTableSettings(
                boardType, checkInMode, checkoutMode, numThrows, throwMode, finderType, tableType
        );

        Assertions.assertEquals(checkoutMode, tableSettings.getCheckoutMode());
    }

    @Test
    void getTheNumThrows() {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = 3;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        FinderType finderType = FinderType.DESCENDING;
        TableType tableType = TableType.ASCENDING;

        TableSettings tableSettings = new StandardTableSettings(
                boardType, checkInMode, checkoutMode, numThrows, throwMode, finderType, tableType
        );

        Assertions.assertEquals(numThrows, tableSettings.getNumThrows());
    }

    @Test
    void doesNotHaveAFixedNumberOfThrows() {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = -1;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        FinderType finderType = FinderType.DESCENDING;
        TableType tableType = TableType.ASCENDING;

        TableSettings tableSettings = new StandardTableSettings(
                boardType, checkInMode, checkoutMode, numThrows, throwMode, finderType, tableType
        );

        Assertions.assertFalse(tableSettings.hasFixedNumThrows());
    }

    @Test
    void hasAFixedNumberOfThrows() {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = 2;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        FinderType finderType = FinderType.DESCENDING;
        TableType tableType = TableType.ASCENDING;

        TableSettings tableSettings = new StandardTableSettings(
                boardType, checkInMode, checkoutMode, numThrows, throwMode, finderType, tableType
        );

        Assertions.assertTrue(tableSettings.hasFixedNumThrows());
    }

    @Test
    void getTheThrowMode() {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = -1;
        ThrowMode throwMode = ThrowMode.FIXED;
        FinderType finderType = FinderType.DESCENDING;
        TableType tableType = TableType.ASCENDING;

        TableSettings tableSettings = new StandardTableSettings(
                boardType, checkInMode, checkoutMode, numThrows, throwMode, finderType, tableType
        );

        Assertions.assertEquals(throwMode, tableSettings.getThrowMode());
    }

    @Test
    void getTheFinderType() {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = -1;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        FinderType finderType = FinderType.CARTESIAN;
        TableType tableType = TableType.ASCENDING;

        TableSettings tableSettings = new StandardTableSettings(
                boardType, checkInMode, checkoutMode, numThrows, throwMode, finderType, tableType
        );

        Assertions.assertEquals(finderType, tableSettings.getFinderType());
    }

    @Test
    void getTheTableType() {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = -1;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        FinderType finderType = FinderType.DESCENDING;
        TableType tableType = TableType.ASCENDING;

        TableSettings tableSettings = new StandardTableSettings(
                boardType, checkInMode, checkoutMode, numThrows, throwMode, finderType, tableType
        );

        Assertions.assertEquals(tableType, tableSettings.getTableType());
    }
}
