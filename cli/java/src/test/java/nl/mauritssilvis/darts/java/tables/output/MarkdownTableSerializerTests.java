/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.output;

import nl.mauritssilvis.darts.java.output.Serializer;
import nl.mauritssilvis.darts.java.settings.*;
import nl.mauritssilvis.darts.java.settings.types.TableSettingsBuilder;
import nl.mauritssilvis.darts.java.tables.Table;
import nl.mauritssilvis.darts.java.tables.TableGenerator;
import nl.mauritssilvis.darts.java.tables.types.TableGeneratorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class MarkdownTableSerializerTests {
    @Test
    void getASerializedTable() {
        TableType tableType = TableType.ASCENDING;
        BoardType boardType = BoardType.YORKSHIRE;
        CheckMode checkoutMode = CheckMode.ANY;

        Settings settings = TableSettingsBuilder.create()
                .setBoardType(boardType)
                .setCheckoutMode(checkoutMode)
                .setTableType(tableType)
                .build();

        TableGenerator tableGenerator = TableGeneratorFactory.create(tableType, settings);

        int minScore = 22;
        int maxScore = 23;

        Table table = tableGenerator.generate(minScore, maxScore);

        Serializer<Table> serializer = MarkdownTableSerializer.create();

        String output = serializer.serialize(table);

        Assertions.assertEquals(
                """
                        | Score |         1 |         2 |  # |
                        |------:|----------:|----------:|---:|
                        |    22 |         * |         * |  1 |
                        |       |       D11 |         - |  1 |
                        |    23 |         * |         * | 38 |
                        |       |       D11 |         1 |  2 |
                        |       |  20 / D10 |         3 |  4 |
                        |       |        19 |   4 /  D2 |  4 |
                        |       |  18 /  D9 |         5 |  4 |
                        |       |        17 |   6 /  D3 |  4 |
                        |       |  16 /  D8 |         7 |  4 |
                        |       |        15 |   8 /  D4 |  4 |
                        |       |  14 /  D7 |         9 |  4 |
                        |       |        13 |  10 /  D5 |  4 |
                        |       |  12 /  D6 |        11 |  4 |
                        """,
                output);
    }

    @ParameterizedTest
    @EnumSource(TableType.class)
    void getTheTableStart(TableType tableType) {
        Settings settings = TableSettingsBuilder.create()
                .setTableType(tableType)
                .build();

        TableGenerator tableGenerator = TableGeneratorFactory.create(tableType, settings);

        int minScore = 2;
        int maxScore = 2;

        Table table = tableGenerator.generate(minScore, maxScore);

        Serializer<Table> serializer = MarkdownTableSerializer.create();

        String output = serializer.serialize(table);

        Assertions.assertTrue(output.startsWith("| Score | "));
    }

    @ParameterizedTest
    @MethodSource("withAnEmptySerializedTable")
    void getAnEmptySerializedTable(int numThrows, ThrowMode throwMode, String output) {
        TableType tableType = TableType.ASCENDING;

        Settings settings = TableSettingsBuilder.create()
                .setNumThrows(numThrows)
                .setThrowMode(throwMode)
                .setTableType(tableType)
                .build();

        TableGenerator tableGenerator = TableGeneratorFactory.create(tableType, settings);

        int minScore = 1;
        int maxScore = 0;

        Table table = tableGenerator.generate(minScore, maxScore);

        Serializer<Table> serializer = MarkdownTableSerializer.create();

        Assertions.assertEquals(output, serializer.serialize(table));
    }

    private static Stream<Arguments> withAnEmptySerializedTable() {
        return Stream.of(
                Arguments.of(
                        0,
                        ThrowMode.OPTIMAL,
                        """
                                | Score | # |
                                |------:|--:|
                                """
                ),
                Arguments.of(
                        0,
                        ThrowMode.FIXED,
                        """
                                | Score | # |
                                |------:|--:|
                                """
                ),
                Arguments.of(
                        1,
                        ThrowMode.OPTIMAL,
                        """
                                | Score | 1 | # |
                                |------:|--:|--:|
                                """
                ),
                Arguments.of(
                        2,
                        ThrowMode.FIXED,
                        """
                                | Score | 1 | 2 | # |
                                |------:|--:|--:|--:|
                                """
                )
        );
    }

    @ParameterizedTest
    @MethodSource("withAnEmptySerializedRow")
    void getAnEmptySerializedRow(int numThrows, ThrowMode throwMode, String output) {
        TableType tableType = TableType.ASCENDING;

        Settings settings = TableSettingsBuilder.create()
                .setNumThrows(numThrows)
                .setThrowMode(throwMode)
                .setTableType(tableType)
                .build();

        TableGenerator tableGenerator = TableGeneratorFactory.create(tableType, settings);

        int score = 0;

        Table table = tableGenerator.generate(score, score);

        Serializer<Table> serializer = MarkdownTableSerializer.create();

        Assertions.assertEquals(output, serializer.serialize(table));
    }

    private static Stream<Arguments> withAnEmptySerializedRow() {
        return Stream.of(
                Arguments.of(
                        0,
                        ThrowMode.OPTIMAL,
                        """
                                | Score | # |
                                |------:|--:|
                                |     0 | 0 |
                                """
                ),
                Arguments.of(
                        0,
                        ThrowMode.FIXED,
                        """
                                | Score | # |
                                |------:|--:|
                                |     0 | 0 |
                                """
                ),
                Arguments.of(
                        1,
                        ThrowMode.OPTIMAL,
                        """
                                | Score | 1 | # |
                                |------:|--:|--:|
                                |     0 | * | 0 |
                                """
                ),
                Arguments.of(
                        4,
                        ThrowMode.FIXED,
                        """
                                | Score | 1 | 2 | 3 | 4 | # |
                                |------:|--:|--:|--:|--:|--:|
                                |     0 | * | * | * | * | 0 |
                                """
                )
        );
    }
}
