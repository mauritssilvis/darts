/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.settings.standard;

import nl.mauritssilvis.darts.java.settings.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BasicTableSettingsBuilderTests {
    @Test
    void getTheBoardType() {
        BoardType boardType = BoardType.QUADRO;

        TableSettingsBuilder tableSettingsBuilder = BasicTableSettingsBuilder.create();

        TableSettings tableSettings = tableSettingsBuilder
                .setBoardType(boardType)
                .build();

        Assertions.assertEquals(boardType, tableSettings.getBoardType());
    }

    @Test
    void getTheDefaultBoardType() {
        TableSettingsBuilder tableSettingsBuilder = BasicTableSettingsBuilder.create();
        TableSettings tableSettings = tableSettingsBuilder.build();

        BoardType boardType = BoardType.LONDON;

        Assertions.assertEquals(boardType, tableSettings.getBoardType());
    }

    @Test
    void overrideTheBoardType() {
        BoardType boardType1 = BoardType.QUADRO;
        BoardType boardType2 = BoardType.YORKSHIRE;

        TableSettingsBuilder tableSettingsBuilder = BasicTableSettingsBuilder.create();

        TableSettings tableSettings = tableSettingsBuilder
                .setBoardType(boardType1)
                .setBoardType(boardType2)
                .build();

        Assertions.assertEquals(boardType2, tableSettings.getBoardType());
    }


    @Test
    void getTheCheckInMode() {
        CheckMode checkInMode = CheckMode.MASTER;

        TableSettingsBuilder tableSettingsBuilder = BasicTableSettingsBuilder.create();

        TableSettings tableSettings = tableSettingsBuilder
                .setCheckInMode(checkInMode)
                .build();

        Assertions.assertEquals(checkInMode, tableSettings.getCheckInMode());
    }

    @Test
    void getTheDefaultCheckInMode() {
        TableSettingsBuilder tableSettingsBuilder = BasicTableSettingsBuilder.create();
        TableSettings tableSettings = tableSettingsBuilder.build();

        CheckMode checkInMode = CheckMode.ANY;

        Assertions.assertEquals(checkInMode, tableSettings.getCheckInMode());
    }

    @Test
    void overrideTheCheckInMode() {
        CheckMode checkInMode1 = CheckMode.DOUBLE;
        CheckMode checkInMode2 = CheckMode.MASTER;

        TableSettingsBuilder tableSettingsBuilder = BasicTableSettingsBuilder.create();

        TableSettings tableSettings = tableSettingsBuilder
                .setCheckInMode(checkInMode1)
                .setCheckInMode(checkInMode2)
                .build();

        Assertions.assertEquals(checkInMode2, tableSettings.getCheckInMode());
    }


    @Test
    void getTheCheckoutMode() {
        CheckMode checkoutMode = CheckMode.ANY;

        TableSettingsBuilder tableSettingsBuilder = BasicTableSettingsBuilder.create();

        TableSettings tableSettings = tableSettingsBuilder
                .setCheckoutMode(checkoutMode)
                .build();

        Assertions.assertEquals(checkoutMode, tableSettings.getCheckoutMode());
    }

    @Test
    void getTheDefaultCheckoutMode() {
        TableSettingsBuilder tableSettingsBuilder = BasicTableSettingsBuilder.create();
        TableSettings tableSettings = tableSettingsBuilder.build();

        CheckMode checkoutMode = CheckMode.DOUBLE;

        Assertions.assertEquals(checkoutMode, tableSettings.getCheckoutMode());
    }

    @Test
    void overrideTheCheckoutMode() {
        CheckMode checkoutMode1 = CheckMode.ANY;
        CheckMode checkoutMode2 = CheckMode.MASTER;

        TableSettingsBuilder tableSettingsBuilder = BasicTableSettingsBuilder.create();

        TableSettings tableSettings = tableSettingsBuilder
                .setCheckoutMode(checkoutMode1)
                .setCheckoutMode(checkoutMode2)
                .build();

        Assertions.assertEquals(checkoutMode2, tableSettings.getCheckoutMode());
    }


    @Test
    void getTheNumThrows() {
        int numThrows = 5;

        TableSettingsBuilder tableSettingsBuilder = BasicTableSettingsBuilder.create();

        TableSettings tableSettings = tableSettingsBuilder
                .setNumThrows(numThrows)
                .build();

        Assertions.assertEquals(numThrows, tableSettings.getNumThrows());
    }

    @Test
    void getTheDefaultNumThrows() {
        TableSettingsBuilder tableSettingsBuilder = BasicTableSettingsBuilder.create();
        TableSettings tableSettings = tableSettingsBuilder.build();

        int numThrows = -1;

        Assertions.assertEquals(numThrows, tableSettings.getNumThrows());
    }

    @Test
    void overrideTheNumThrows() {
        int numThrows1 = 5;
        int numThrows2 = 3;

        TableSettingsBuilder tableSettingsBuilder = BasicTableSettingsBuilder.create();

        TableSettings tableSettings = tableSettingsBuilder
                .setNumThrows(numThrows1)
                .setNumThrows(numThrows2)
                .build();

        Assertions.assertEquals(numThrows2, tableSettings.getNumThrows());
    }


    @Test
    void getTheThrowMode() {
        ThrowMode throwMode = ThrowMode.FIXED;

        TableSettingsBuilder tableSettingsBuilder = BasicTableSettingsBuilder.create();

        TableSettings tableSettings = tableSettingsBuilder
                .setThrowMode(throwMode)
                .build();

        Assertions.assertEquals(throwMode, tableSettings.getThrowMode());
    }

    @Test
    void getTheDefaultThrowMode() {
        TableSettingsBuilder tableSettingsBuilder = BasicTableSettingsBuilder.create();
        TableSettings tableSettings = tableSettingsBuilder.build();

        ThrowMode throwMode = ThrowMode.OPTIMAL;

        Assertions.assertEquals(throwMode, tableSettings.getThrowMode());
    }

    @Test
    void overrideTheThrowMode() {
        ThrowMode throwMode1 = ThrowMode.OPTIMAL;
        ThrowMode throwMode2 = ThrowMode.FIXED;

        TableSettingsBuilder tableSettingsBuilder = BasicTableSettingsBuilder.create();

        TableSettings tableSettings = tableSettingsBuilder
                .setThrowMode(throwMode1)
                .setThrowMode(throwMode2)
                .build();

        Assertions.assertEquals(throwMode2, tableSettings.getThrowMode());
    }

    @Test
    void getTheFinderType() {
        FinderType finderType = FinderType.CARTESIAN;

        TableSettingsBuilder tableSettingsBuilder = BasicTableSettingsBuilder.create();

        TableSettings tableSettings = tableSettingsBuilder
                .setFinderType(finderType)
                .build();

        Assertions.assertEquals(finderType, tableSettings.getFinderType());
    }

    @Test
    void getTheDefaultFinderType() {
        TableSettingsBuilder tableSettingsBuilder = BasicTableSettingsBuilder.create();
        TableSettings tableSettings = tableSettingsBuilder.build();

        FinderType finderType = FinderType.DESCENDING;

        Assertions.assertEquals(finderType, tableSettings.getFinderType());
    }

    @Test
    void overrideTheFinderType() {
        FinderType finderType1 = FinderType.DESCENDING;
        FinderType finderType2 = FinderType.CARTESIAN;

        TableSettingsBuilder tableSettingsBuilder = BasicTableSettingsBuilder.create();

        TableSettings tableSettings = tableSettingsBuilder
                .setFinderType(finderType1)
                .setFinderType(finderType2)
                .build();

        Assertions.assertEquals(finderType2, tableSettings.getFinderType());
    }


    @Test
    void getTheTableType() {
        TableType tableType = TableType.ASCENDING;

        TableSettingsBuilder tableSettingsBuilder = BasicTableSettingsBuilder.create();

        TableSettings tableSettings = tableSettingsBuilder
                .setTableType(tableType)
                .build();

        Assertions.assertEquals(tableType, tableSettings.getTableType());
    }

    @Test
    void getTheDefaultTableType() {
        TableSettingsBuilder tableSettingsBuilder = BasicTableSettingsBuilder.create();
        TableSettings tableSettings = tableSettingsBuilder.build();

        TableType tableType = TableType.ASCENDING;

        Assertions.assertEquals(tableType, tableSettings.getTableType());
    }

    @Test
    void overrideTheTableType() {
        TableType tableType1 = null;
        TableType tableType2 = TableType.ASCENDING;

        TableSettingsBuilder tableSettingsBuilder = BasicTableSettingsBuilder.create();

        TableSettings tableSettings = tableSettingsBuilder
                .setTableType(tableType1)
                .setTableType(tableType2)
                .build();

        Assertions.assertEquals(tableType2, tableSettings.getTableType());
    }

    @Test
    void getUnequalSettingsBuilders() {
        TableSettingsBuilder tableSettingsBuilder1 = BasicTableSettingsBuilder.create();
        TableSettingsBuilder tableSettingsBuilder2 = BasicTableSettingsBuilder.create();

        Assertions.assertNotEquals(tableSettingsBuilder1, tableSettingsBuilder2);
    }

    @Test
    void convertToAString() {
        TableSettingsBuilder tableSettingsBuilder = BasicTableSettingsBuilder.create();
        String str = tableSettingsBuilder.toString();

        Assertions.assertAll(
                () -> Assertions.assertTrue(str.contains(tableSettingsBuilder.getClass().getSimpleName())),
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
