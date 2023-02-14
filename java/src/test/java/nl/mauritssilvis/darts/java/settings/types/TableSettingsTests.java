/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.settings.types;

import nl.mauritssilvis.darts.java.settings.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TableSettingsTests {
    @ParameterizedTest
    @ValueSource(ints = {-1, -2, -5, -10})
    void doNotAcceptANegativeNumberOfThrows() {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = -1;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        FinderType finderType = FinderType.DESCENDING;
        TableType tableType = TableType.ASCENDING;

        Assertions.assertThrows(IllegalArgumentException.class, () -> TableSettings.of(
                        boardType, checkInMode, checkoutMode, numThrows, throwMode, finderType, tableType
                )
        );
    }

    @Test
    void getTheBoardType() {
        BoardType boardType = BoardType.QUADRO;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = 0;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        FinderType finderType = FinderType.DESCENDING;
        TableType tableType = TableType.ASCENDING;

        Settings settings = TableSettings.of(
                boardType, checkInMode, checkoutMode, numThrows, throwMode, finderType, tableType
        );

        Assertions.assertEquals(boardType, settings.getBoardType());
    }

    @Test
    void getTheCheckInMode() {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.MASTER;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = 0;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        FinderType finderType = FinderType.DESCENDING;
        TableType tableType = TableType.ASCENDING;

        Settings settings = TableSettings.of(
                boardType, checkInMode, checkoutMode, numThrows, throwMode, finderType, tableType
        );

        Assertions.assertEquals(checkInMode, settings.getCheckInMode());
    }

    @Test
    void getTheCheckoutMode() {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.ANY;
        int numThrows = 0;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        FinderType finderType = FinderType.DESCENDING;
        TableType tableType = TableType.ASCENDING;

        Settings settings = TableSettings.of(
                boardType, checkInMode, checkoutMode, numThrows, throwMode, finderType, tableType
        );

        Assertions.assertEquals(checkoutMode, settings.getCheckoutMode());
    }

    @Test
    void getTheNumberOfThrows() {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = 3;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        FinderType finderType = FinderType.DESCENDING;
        TableType tableType = TableType.ASCENDING;

        Settings settings = TableSettings.of(
                boardType, checkInMode, checkoutMode, numThrows, throwMode, finderType, tableType
        );

        Assertions.assertEquals(numThrows, settings.getNumThrows());
    }

    @Test
    void doNotGetAFixedNumberOfThrows() {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = 0;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        FinderType finderType = FinderType.DESCENDING;
        TableType tableType = TableType.ASCENDING;

        Settings settings = TableSettings.of(
                boardType, checkInMode, checkoutMode, numThrows, throwMode, finderType, tableType
        );

        Assertions.assertFalse(settings.hasFixedNumThrows());
    }

    @Test
    void getAFixedNumberOfThrows() {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = 2;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        FinderType finderType = FinderType.DESCENDING;
        TableType tableType = TableType.ASCENDING;

        Settings settings = TableSettings.of(
                boardType, checkInMode, checkoutMode, numThrows, throwMode, finderType, tableType
        );

        Assertions.assertTrue(settings.hasFixedNumThrows());
    }

    @Test
    void getTheThrowMode() {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = 0;
        ThrowMode throwMode = ThrowMode.FIXED;
        FinderType finderType = FinderType.DESCENDING;
        TableType tableType = TableType.ASCENDING;

        Settings settings = TableSettings.of(
                boardType, checkInMode, checkoutMode, numThrows, throwMode, finderType, tableType
        );

        Assertions.assertEquals(throwMode, settings.getThrowMode());
    }

    @Test
    void getTheFinderType() {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = 0;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        FinderType finderType = FinderType.CARTESIAN;
        TableType tableType = TableType.ASCENDING;

        Settings settings = TableSettings.of(
                boardType, checkInMode, checkoutMode, numThrows, throwMode, finderType, tableType
        );

        Assertions.assertEquals(finderType, settings.getFinderType());
    }

    @Test
    void getTheTableType() {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = 0;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        FinderType finderType = FinderType.DESCENDING;
        TableType tableType = TableType.ASCENDING;

        Settings settings = TableSettings.of(
                boardType, checkInMode, checkoutMode, numThrows, throwMode, finderType, tableType
        );

        Assertions.assertEquals(tableType, settings.getTableType());
    }

    @Test
    void getEqualSettings() {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = 0;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        FinderType finderType = FinderType.DESCENDING;
        TableType tableType = TableType.ASCENDING;

        Settings settings1 = TableSettings.of(
                boardType, checkInMode, checkoutMode, numThrows, throwMode, finderType, tableType
        );

        Settings settings2 = TableSettings.of(
                boardType, checkInMode, checkoutMode, numThrows, throwMode, finderType, tableType
        );

        Assertions.assertEquals(settings1, settings2);
    }

    @Test
    void getUnequalSettings() {
        BoardType boardType1 = BoardType.LONDON;
        CheckMode checkInMode1 = CheckMode.ANY;
        CheckMode checkoutMode1 = CheckMode.DOUBLE;
        int numThrows1 = 0;
        ThrowMode throwMode1 = ThrowMode.OPTIMAL;
        FinderType finderType1 = FinderType.DESCENDING;
        TableType tableType1 = TableType.ASCENDING;

        Settings settings1 = TableSettings.of(
                boardType1, checkInMode1, checkoutMode1, numThrows1, throwMode1, finderType1, tableType1
        );

        BoardType boardType2 = BoardType.QUADRO;
        CheckMode checkInMode2 = CheckMode.MASTER;
        CheckMode checkoutMode2 = CheckMode.MASTER;
        int numThrows2 = 2;
        ThrowMode throwMode2 = ThrowMode.FIXED;
        FinderType finderType2 = FinderType.CARTESIAN;
        TableType tableType2 = TableType.ASCENDING;

        Settings settings2 = TableSettings.of(
                boardType2, checkInMode2, checkoutMode2, numThrows2, throwMode2, finderType2, tableType2
        );

        Assertions.assertNotEquals(settings1, settings2);
    }

    @Test
    void getUnequalSettingsWithDifferentBoardTypes() {
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = 0;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        FinderType finderType = FinderType.DESCENDING;
        TableType tableType = TableType.ASCENDING;

        BoardType boardType1 = BoardType.YORKSHIRE;

        Settings settings1 = TableSettings.of(
                boardType1, checkInMode, checkoutMode, numThrows, throwMode, finderType, tableType
        );

        BoardType boardType2 = BoardType.QUADRO;

        Settings settings2 = TableSettings.of(
                boardType2, checkInMode, checkoutMode, numThrows, throwMode, finderType, tableType
        );

        Assertions.assertNotEquals(settings1, settings2);
    }

    @Test
    void getUnequalSettingsWithDifferentCheckInModes() {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = 0;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        FinderType finderType = FinderType.DESCENDING;
        TableType tableType = TableType.ASCENDING;

        CheckMode checkInMode1 = CheckMode.MASTER;

        Settings settings1 = TableSettings.of(
                boardType, checkInMode1, checkoutMode, numThrows, throwMode, finderType, tableType
        );

        CheckMode checkInMode2 = CheckMode.DOUBLE;

        Settings settings2 = TableSettings.of(
                boardType, checkInMode2, checkoutMode, numThrows, throwMode, finderType, tableType
        );

        Assertions.assertNotEquals(settings1, settings2);
    }

    @Test
    void getUnequalSettingsWithDifferentCheckoutModes() {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.ANY;
        int numThrows = 0;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        FinderType finderType = FinderType.DESCENDING;
        TableType tableType = TableType.ASCENDING;

        CheckMode checkoutMode1 = CheckMode.MASTER;

        Settings settings1 = TableSettings.of(
                boardType, checkInMode, checkoutMode1, numThrows, throwMode, finderType, tableType
        );

        CheckMode checkoutMode2 = CheckMode.ANY;

        Settings settings2 = TableSettings.of(
                boardType, checkInMode, checkoutMode2, numThrows, throwMode, finderType, tableType
        );

        Assertions.assertNotEquals(settings1, settings2);
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

        Settings settings1 = TableSettings.of(
                boardType, checkInMode, checkoutMode, numThrows1, throwMode, finderType, tableType
        );

        int numThrows2 = 3;

        Settings settings2 = TableSettings.of(
                boardType, checkInMode, checkoutMode, numThrows2, throwMode, finderType, tableType
        );

        Assertions.assertNotEquals(settings1, settings2);
    }

    @Test
    void getUnequalSettingsWithADifferentThrowMode() {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = 0;
        FinderType finderType = FinderType.DESCENDING;
        TableType tableType = TableType.ASCENDING;

        ThrowMode throwMode1 = ThrowMode.FIXED;

        Settings settings1 = TableSettings.of(
                boardType, checkInMode, checkoutMode, numThrows, throwMode1, finderType, tableType
        );

        ThrowMode throwMode2 = ThrowMode.OPTIMAL;

        Settings settings2 = TableSettings.of(
                boardType, checkInMode, checkoutMode, numThrows, throwMode2, finderType, tableType
        );

        Assertions.assertNotEquals(settings1, settings2);
    }

    @Test
    void getUnequalSettingsWithADifferentFinderType() {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = 0;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        TableType tableType = TableType.ASCENDING;

        FinderType finderType1 = FinderType.CARTESIAN;

        Settings settings1 = TableSettings.of(
                boardType, checkInMode, checkoutMode, numThrows, throwMode, finderType1, tableType
        );

        FinderType finderType2 = FinderType.DESCENDING;

        Settings settings2 = TableSettings.of(
                boardType, checkInMode, checkoutMode, numThrows, throwMode, finderType2, tableType
        );

        Assertions.assertNotEquals(settings1, settings2);
    }

    @Test
    void getUnequalSettingsWithADifferentTableType() {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = 0;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        FinderType finderType = FinderType.DESCENDING;

        TableType tableType1 = null;

        Settings settings1 = TableSettings.of(
                boardType, checkInMode, checkoutMode, numThrows, throwMode, finderType, tableType1
        );

        TableType tableType2 = TableType.ASCENDING;

        Settings settings2 = TableSettings.of(
                boardType, checkInMode, checkoutMode, numThrows, throwMode, finderType, tableType2
        );

        Assertions.assertNotEquals(settings1, settings2);
    }

    @Test
    void convertToAString() {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = 0;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        FinderType finderType = FinderType.DESCENDING;
        TableType tableType = TableType.ASCENDING;

        Settings settings = TableSettings.of(
                boardType, checkInMode, checkoutMode, numThrows, throwMode, finderType, tableType
        );

        String str = settings.toString();

        Assertions.assertAll(
                () -> Assertions.assertTrue(str.startsWith(settings.getClass().getSimpleName())),
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
