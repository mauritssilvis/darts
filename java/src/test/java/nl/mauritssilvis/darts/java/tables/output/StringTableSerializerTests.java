/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.output;

import nl.mauritssilvis.darts.java.output.Serializer;
import nl.mauritssilvis.darts.java.settings.*;
import nl.mauritssilvis.darts.java.tables.Table;
import nl.mauritssilvis.darts.java.tables.TableGenerator;
import nl.mauritssilvis.darts.java.tables.factory.TableGeneratorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class StringTableSerializerTests {
    @ParameterizedTest
    @EnumSource(TableType.class)
    void includeTheTableName(TableType tableType) {
        BoardType boardType = BoardType.YORKSHIRE;
        CheckMode checkInMode = CheckMode.DOUBLE;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = -1;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        FinderType finderType = FinderType.DESCENDING;

        TableGenerator tableGenerator = TableGeneratorFactory.create(
                tableType, boardType, checkInMode, checkoutMode, numThrows, throwMode, finderType
        );

        int minScore = 2;
        int maxScore = 2;

        Table table = tableGenerator.generate(minScore, maxScore);

        Serializer<Table> serializer = StringTableSerializer.create();

        String output = serializer.serialize(table);

        String tableName = getTableName(tableType);

        Assertions.assertTrue(output.startsWith(tableName));
    }

    @ParameterizedTest
    @EnumSource(TableType.class)
    void useTheStringRepresentation(TableType tableType) {
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.MASTER;
        int numThrows = -1;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        FinderType finderType = FinderType.CARTESIAN;

        TableGenerator tableGenerator = TableGeneratorFactory.create(
                tableType, boardType, checkInMode, checkoutMode, numThrows, throwMode, finderType
        );

        int minScore = 3;
        int maxScore = 3;

        Table table = tableGenerator.generate(minScore, maxScore);

        Serializer<Table> serializer = StringTableSerializer.create();

        String output = serializer.serialize(table);

        String str = table.toString();
        int index = str.indexOf('(');
        String start = str.substring(0, index + 1);

        Assertions.assertTrue(output.startsWith(start));
    }

    @ParameterizedTest
    @EnumSource(TableType.class)
    void indentTheOutput(TableType tableType) {
        BoardType boardType = BoardType.QUADRO;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.ANY;
        int numThrows = -1;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        FinderType finderType = FinderType.DESCENDING;

        TableGenerator tableGenerator = TableGeneratorFactory.create(
                tableType, boardType, checkInMode, checkoutMode, numThrows, throwMode, finderType
        );

        int minScore = 1;
        int maxScore = 1;

        Table table = tableGenerator.generate(minScore, maxScore);

        Serializer<Table> serializer = StringTableSerializer.create();

        String output = serializer.serialize(table);

        String indentation = "(\n  ";

        Assertions.assertTrue(output.contains(indentation));
    }

    private static String getTableName(TableType tableType) {
        String fullName = tableType.toString();
        String shortName = fullName.split("\\.")[1];

        char first = shortName.charAt(0);
        String rest = shortName.substring(1);

        return first + rest.toLowerCase();
    }
}
