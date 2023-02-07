/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.output;

import nl.mauritssilvis.darts.java.output.Serializer;
import nl.mauritssilvis.darts.java.settings.BoardType;
import nl.mauritssilvis.darts.java.settings.CheckType;
import nl.mauritssilvis.darts.java.settings.FinderType;
import nl.mauritssilvis.darts.java.settings.TableType;
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
        CheckType checkInType = CheckType.DOUBLE;
        CheckType checkoutType = CheckType.DOUBLE;
        FinderType finderType = FinderType.DESCENDING;

        TableGenerator tableGenerator = TableGeneratorFactory.create(
                tableType, boardType, checkInType, checkoutType, finderType
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
        CheckType checkInType = CheckType.ANY;
        CheckType checkoutType = CheckType.MASTER;
        FinderType finderType = FinderType.CARTESIAN;

        TableGenerator tableGenerator = TableGeneratorFactory.create(
                tableType, boardType, checkInType, checkoutType, finderType
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
        CheckType checkInType = CheckType.ANY;
        CheckType checkoutType = CheckType.ANY;
        FinderType finderType = FinderType.DESCENDING;

        TableGenerator tableGenerator = TableGeneratorFactory.create(
                tableType, boardType, checkInType, checkoutType, finderType
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
