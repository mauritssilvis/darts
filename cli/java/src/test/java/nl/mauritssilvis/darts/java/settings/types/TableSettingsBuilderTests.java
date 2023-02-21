/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.settings.types;

import nl.mauritssilvis.darts.java.settings.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TableSettingsBuilderTests {
    @Test
    void getTheBoardType() {
        BoardType boardType = BoardType.QUADRO;

        SettingsBuilder settingsBuilder = TableSettingsBuilder.create();

        Settings settings = settingsBuilder
                .setBoardType(boardType)
                .build();

        Assertions.assertEquals(boardType, settings.getBoardType());
    }

    @Test
    void getTheDefaultBoardType() {
        SettingsBuilder settingsBuilder = TableSettingsBuilder.create();
        Settings settings = settingsBuilder.build();

        BoardType boardType = BoardType.LONDON;

        Assertions.assertEquals(boardType, settings.getBoardType());
    }

    @Test
    void overrideTheBoardType() {
        BoardType boardType1 = BoardType.QUADRO;
        BoardType boardType2 = BoardType.YORKSHIRE;

        SettingsBuilder settingsBuilder = TableSettingsBuilder.create();

        Settings settings = settingsBuilder
                .setBoardType(boardType1)
                .setBoardType(boardType2)
                .build();

        Assertions.assertEquals(boardType2, settings.getBoardType());
    }

    @Test
    void getTheCheckInMode() {
        CheckMode checkInMode = CheckMode.MASTER;

        SettingsBuilder settingsBuilder = TableSettingsBuilder.create();

        Settings settings = settingsBuilder
                .setCheckInMode(checkInMode)
                .build();

        Assertions.assertEquals(checkInMode, settings.getCheckInMode());
    }

    @Test
    void getTheDefaultCheckInMode() {
        SettingsBuilder settingsBuilder = TableSettingsBuilder.create();
        Settings settings = settingsBuilder.build();

        CheckMode checkInMode = CheckMode.ANY;

        Assertions.assertEquals(checkInMode, settings.getCheckInMode());
    }

    @Test
    void overrideTheCheckInMode() {
        CheckMode checkInMode1 = CheckMode.DOUBLE;
        CheckMode checkInMode2 = CheckMode.MASTER;

        SettingsBuilder settingsBuilder = TableSettingsBuilder.create();

        Settings settings = settingsBuilder
                .setCheckInMode(checkInMode1)
                .setCheckInMode(checkInMode2)
                .build();

        Assertions.assertEquals(checkInMode2, settings.getCheckInMode());
    }

    @Test
    void getTheCheckoutMode() {
        CheckMode checkoutMode = CheckMode.ANY;

        SettingsBuilder settingsBuilder = TableSettingsBuilder.create();

        Settings settings = settingsBuilder
                .setCheckoutMode(checkoutMode)
                .build();

        Assertions.assertEquals(checkoutMode, settings.getCheckoutMode());
    }

    @Test
    void getTheDefaultCheckoutMode() {
        SettingsBuilder settingsBuilder = TableSettingsBuilder.create();
        Settings settings = settingsBuilder.build();

        CheckMode checkoutMode = CheckMode.DOUBLE;

        Assertions.assertEquals(checkoutMode, settings.getCheckoutMode());
    }

    @Test
    void overrideTheCheckoutMode() {
        CheckMode checkoutMode1 = CheckMode.ANY;
        CheckMode checkoutMode2 = CheckMode.MASTER;

        SettingsBuilder settingsBuilder = TableSettingsBuilder.create();

        Settings settings = settingsBuilder
                .setCheckoutMode(checkoutMode1)
                .setCheckoutMode(checkoutMode2)
                .build();

        Assertions.assertEquals(checkoutMode2, settings.getCheckoutMode());
    }

    @Test
    void getTheNumberOfThrows() {
        int numThrows = 5;

        SettingsBuilder settingsBuilder = TableSettingsBuilder.create();

        Settings settings = settingsBuilder
                .setNumThrows(numThrows)
                .build();

        Assertions.assertEquals(numThrows, settings.getNumThrows());
    }

    @Test
    void getTheDefaultNumberOfThrows() {
        SettingsBuilder settingsBuilder = TableSettingsBuilder.create();
        Settings settings = settingsBuilder.build();

        int numThrows = 0;

        Assertions.assertEquals(numThrows, settings.getNumThrows());
    }

    @Test
    void overrideTheNumberOfThrows() {
        int numThrows1 = 5;
        int numThrows2 = 3;

        SettingsBuilder settingsBuilder = TableSettingsBuilder.create();

        Settings settings = settingsBuilder
                .setNumThrows(numThrows1)
                .setNumThrows(numThrows2)
                .build();

        Assertions.assertEquals(numThrows2, settings.getNumThrows());
    }

    @Test
    void getTheThrowMode() {
        ThrowMode throwMode = ThrowMode.FIXED;

        SettingsBuilder settingsBuilder = TableSettingsBuilder.create();

        Settings settings = settingsBuilder
                .setThrowMode(throwMode)
                .build();

        Assertions.assertEquals(throwMode, settings.getThrowMode());
    }

    @Test
    void getTheDefaultThrowMode() {
        SettingsBuilder settingsBuilder = TableSettingsBuilder.create();
        Settings settings = settingsBuilder.build();

        ThrowMode throwMode = ThrowMode.OPTIMAL;

        Assertions.assertEquals(throwMode, settings.getThrowMode());
    }

    @Test
    void overrideTheThrowMode() {
        ThrowMode throwMode1 = ThrowMode.OPTIMAL;
        ThrowMode throwMode2 = ThrowMode.FIXED;

        SettingsBuilder settingsBuilder = TableSettingsBuilder.create();

        Settings settings = settingsBuilder
                .setThrowMode(throwMode1)
                .setThrowMode(throwMode2)
                .build();

        Assertions.assertEquals(throwMode2, settings.getThrowMode());
    }

    @Test
    void getTheFinderType() {
        FinderType finderType = FinderType.CARTESIAN;

        SettingsBuilder settingsBuilder = TableSettingsBuilder.create();

        Settings settings = settingsBuilder
                .setFinderType(finderType)
                .build();

        Assertions.assertEquals(finderType, settings.getFinderType());
    }

    @Test
    void getTheDefaultFinderType() {
        SettingsBuilder settingsBuilder = TableSettingsBuilder.create();
        Settings settings = settingsBuilder.build();

        FinderType finderType = FinderType.DESCENDING;

        Assertions.assertEquals(finderType, settings.getFinderType());
    }

    @Test
    void overrideTheFinderType() {
        FinderType finderType1 = FinderType.DESCENDING;
        FinderType finderType2 = FinderType.CARTESIAN;

        SettingsBuilder settingsBuilder = TableSettingsBuilder.create();

        Settings settings = settingsBuilder
                .setFinderType(finderType1)
                .setFinderType(finderType2)
                .build();

        Assertions.assertEquals(finderType2, settings.getFinderType());
    }

    @Test
    void getTheTableType() {
        TableType tableType = TableType.ASCENDING;

        SettingsBuilder settingsBuilder = TableSettingsBuilder.create();

        Settings settings = settingsBuilder
                .setTableType(tableType)
                .build();

        Assertions.assertEquals(tableType, settings.getTableType());
    }

    @Test
    void getTheDefaultTableType() {
        SettingsBuilder settingsBuilder = TableSettingsBuilder.create();
        Settings settings = settingsBuilder.build();

        TableType tableType = TableType.ASCENDING;

        Assertions.assertEquals(tableType, settings.getTableType());
    }

    @Test
    void overrideTheTableType() {
        TableType tableType1 = null;
        TableType tableType2 = TableType.ASCENDING;

        SettingsBuilder settingsBuilder = TableSettingsBuilder.create();

        Settings settings = settingsBuilder
                .setTableType(tableType1)
                .setTableType(tableType2)
                .build();

        Assertions.assertEquals(tableType2, settings.getTableType());
    }

    @Test
    void getUnequalSettingsBuilders() {
        SettingsBuilder settingsBuilder1 = TableSettingsBuilder.create();
        SettingsBuilder settingsBuilder2 = TableSettingsBuilder.create();

        Assertions.assertNotEquals(settingsBuilder1, settingsBuilder2);
    }

    @Test
    void convertToAString() {
        SettingsBuilder settingsBuilder = TableSettingsBuilder.create();
        String str = settingsBuilder.toString();

        Assertions.assertAll(
                () -> Assertions.assertTrue(str.startsWith(settingsBuilder.getClass().getSimpleName())),
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
