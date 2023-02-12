/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.settings.standard;

import nl.mauritssilvis.darts.java.settings.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BasicTableSettingsTests {
    @Test
    void getTheBoardType() {
        BoardType boardType = BoardType.QUADRO;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = -1;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        FinderType finderType = FinderType.DESCENDING;
        TableType tableType = TableType.ASCENDING;

        TableSettings tableSettings = BasicTableSettings.of(
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

        TableSettings tableSettings = BasicTableSettings.of(
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

        TableSettings tableSettings = BasicTableSettings.of(
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

        TableSettings tableSettings = BasicTableSettings.of(
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

        TableSettings tableSettings = BasicTableSettings.of(
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

        TableSettings tableSettings = BasicTableSettings.of(
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

        TableSettings tableSettings = BasicTableSettings.of(
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

        TableSettings tableSettings = BasicTableSettings.of(
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

        TableSettings tableSettings = BasicTableSettings.of(
                boardType, checkInMode, checkoutMode, numThrows, throwMode, finderType, tableType
        );

        Assertions.assertEquals(tableType, tableSettings.getTableType());
    }

    @Test
    void getEqualSettings() {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = -1;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        FinderType finderType = FinderType.DESCENDING;
        TableType tableType = TableType.ASCENDING;

        TableSettings tableSettings1 = BasicTableSettings.of(
                boardType, checkInMode, checkoutMode, numThrows, throwMode, finderType, tableType
        );

        TableSettings tableSettings2 = BasicTableSettings.of(
                boardType, checkInMode, checkoutMode, numThrows, throwMode, finderType, tableType
        );

        Assertions.assertEquals(tableSettings1, tableSettings2);
    }

    @Test
    void getUnequalSettings() {
        BoardType boardType1 = BoardType.LONDON;
        CheckMode checkInMode1 = CheckMode.ANY;
        CheckMode checkoutMode1 = CheckMode.DOUBLE;
        int numThrows1 = -1;
        ThrowMode throwMode1 = ThrowMode.OPTIMAL;
        FinderType finderType1 = FinderType.DESCENDING;
        TableType tableType1 = TableType.ASCENDING;

        TableSettings tableSettings1 = BasicTableSettings.of(
                boardType1, checkInMode1, checkoutMode1, numThrows1, throwMode1, finderType1, tableType1
        );

        BoardType boardType2 = BoardType.QUADRO;
        CheckMode checkInMode2 = CheckMode.MASTER;
        CheckMode checkoutMode2 = CheckMode.MASTER;
        int numThrows2 = 2;
        ThrowMode throwMode2 = ThrowMode.FIXED;
        FinderType finderType2 = FinderType.CARTESIAN;
        TableType tableType2 = TableType.ASCENDING;

        TableSettings tableSettings2 = BasicTableSettings.of(
                boardType2, checkInMode2, checkoutMode2, numThrows2, throwMode2, finderType2, tableType2
        );

        Assertions.assertNotEquals(tableSettings1, tableSettings2);
    }

    @Test
    void getUnequalSettingsWithDifferentBoardTypes() {
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = -1;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        FinderType finderType = FinderType.DESCENDING;
        TableType tableType = TableType.ASCENDING;

        BoardType boardType1 = BoardType.YORKSHIRE;

        TableSettings tableSettings1 = BasicTableSettings.of(
                boardType1, checkInMode, checkoutMode, numThrows, throwMode, finderType, tableType
        );

        BoardType boardType2 = BoardType.QUADRO;

        TableSettings tableSettings2 = BasicTableSettings.of(
                boardType2, checkInMode, checkoutMode, numThrows, throwMode, finderType, tableType
        );

        Assertions.assertNotEquals(tableSettings1, tableSettings2);
    }

    @Test
    void getUnequalSettingsWithDifferentCheckInModes() {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = -1;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        FinderType finderType = FinderType.DESCENDING;
        TableType tableType = TableType.ASCENDING;

        CheckMode checkInMode1 = CheckMode.MASTER;

        TableSettings tableSettings1 = BasicTableSettings.of(
                boardType, checkInMode1, checkoutMode, numThrows, throwMode, finderType, tableType
        );

        CheckMode checkInMode2 = CheckMode.DOUBLE;

        TableSettings tableSettings2 = BasicTableSettings.of(
                boardType, checkInMode2, checkoutMode, numThrows, throwMode, finderType, tableType
        );

        Assertions.assertNotEquals(tableSettings1, tableSettings2);
    }

    @Test
    void getUnequalSettingsWithDifferentCheckoutModes() {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.ANY;
        int numThrows = -1;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        FinderType finderType = FinderType.DESCENDING;
        TableType tableType = TableType.ASCENDING;

        CheckMode checkoutMode1 = CheckMode.MASTER;

        TableSettings tableSettings1 = BasicTableSettings.of(
                boardType, checkInMode, checkoutMode1, numThrows, throwMode, finderType, tableType
        );

        CheckMode checkoutMode2 = CheckMode.ANY;

        TableSettings tableSettings2 = BasicTableSettings.of(
                boardType, checkInMode, checkoutMode2, numThrows, throwMode, finderType, tableType
        );

        Assertions.assertNotEquals(tableSettings1, tableSettings2);
    }

    @Test
    void getUnequalSettingsWithADifferentNumberOfThrows() {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        FinderType finderType = FinderType.DESCENDING;
        TableType tableType = TableType.ASCENDING;

        int numThrows1 = 2;

        TableSettings tableSettings1 = BasicTableSettings.of(
                boardType, checkInMode, checkoutMode, numThrows1, throwMode, finderType, tableType
        );

        int numThrows2 = 3;

        TableSettings tableSettings2 = BasicTableSettings.of(
                boardType, checkInMode, checkoutMode, numThrows2, throwMode, finderType, tableType
        );

        Assertions.assertNotEquals(tableSettings1, tableSettings2);
    }

    @Test
    void getUnequalSettingsWithADifferentThrowMode() {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = -1;
        FinderType finderType = FinderType.DESCENDING;
        TableType tableType = TableType.ASCENDING;

        ThrowMode throwMode1 = ThrowMode.FIXED;

        TableSettings tableSettings1 = BasicTableSettings.of(
                boardType, checkInMode, checkoutMode, numThrows, throwMode1, finderType, tableType
        );

        ThrowMode throwMode2 = ThrowMode.OPTIMAL;

        TableSettings tableSettings2 = BasicTableSettings.of(
                boardType, checkInMode, checkoutMode, numThrows, throwMode2, finderType, tableType
        );

        Assertions.assertNotEquals(tableSettings1, tableSettings2);
    }

    @Test
    void getUnequalSettingsWithADifferentFinderType() {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = -1;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        TableType tableType = TableType.ASCENDING;

        FinderType finderType1 = FinderType.CARTESIAN;

        TableSettings tableSettings1 = BasicTableSettings.of(
                boardType, checkInMode, checkoutMode, numThrows, throwMode, finderType1, tableType
        );

        FinderType finderType2 = FinderType.DESCENDING;

        TableSettings tableSettings2 = BasicTableSettings.of(
                boardType, checkInMode, checkoutMode, numThrows, throwMode, finderType2, tableType
        );

        Assertions.assertNotEquals(tableSettings1, tableSettings2);
    }

    @Test
    void convertToAString() {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = -1;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        FinderType finderType = FinderType.DESCENDING;
        TableType tableType = TableType.ASCENDING;

        TableSettings tableSettings = BasicTableSettings.of(
                boardType, checkInMode, checkoutMode, numThrows, throwMode, finderType, tableType
        );

        String str = tableSettings.toString();

        Assertions.assertAll(
                () -> Assertions.assertTrue(str.contains(tableSettings.getClass().getSimpleName())),
                () -> Assertions.assertTrue(str.contains("boardType")),
                () -> Assertions.assertTrue(str.contains("checkInMode")),
                () -> Assertions.assertTrue(str.contains("checkoutMode")),
                () -> Assertions.assertTrue(str.contains("numThrows")),
                () -> Assertions.assertTrue(str.contains("throwMode")),
                () -> Assertions.assertTrue(str.contains("finderType")),
                () -> Assertions.assertTrue(str.contains("tableType"))
        );
    }
}
