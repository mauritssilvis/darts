/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.output;

import nl.mauritssilvis.darts.java.output.Serializer;
import nl.mauritssilvis.darts.java.settings.Settings;
import nl.mauritssilvis.darts.java.settings.TableType;
import nl.mauritssilvis.darts.java.settings.tables.TableSettingsBuilder;
import nl.mauritssilvis.darts.java.tables.Table;
import nl.mauritssilvis.darts.java.tables.TableGenerator;
import nl.mauritssilvis.darts.java.tables.types.TableGeneratorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class StringTableSerializerTests {
    @ParameterizedTest
    @EnumSource(TableType.class)
    void includeTheTableName(TableType tableType) {
        Settings settings = TableSettingsBuilder.create()
                .setTableType(tableType)
                .build();

        TableGenerator tableGenerator = TableGeneratorFactory.create(tableType, settings);

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
        Settings settings = TableSettingsBuilder.create()
                .setTableType(tableType)
                .build();

        TableGenerator tableGenerator = TableGeneratorFactory.create(tableType, settings);

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
        Settings settings = TableSettingsBuilder.create()
                .setTableType(tableType)
                .build();

        TableGenerator tableGenerator = TableGeneratorFactory.create(tableType, settings);

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
