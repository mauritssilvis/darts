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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class MarkdownTableSerializerTests {
    @Test
    void getAMarkdownTable() {
        TableType tableType = TableType.ASCENDING;
        BoardType boardType = BoardType.YORKSHIRE;
        CheckType checkInType = CheckType.ANY;
        CheckType checkoutType = CheckType.ANY;
        FinderType finderType = FinderType.DESCENDING;

        TableGenerator tableGenerator = TableGeneratorFactory.create(
                tableType, boardType, checkInType, checkoutType, finderType
        );

        int minScore = 22;
        int maxScore = 23;

        Table table = tableGenerator.generate(minScore, maxScore);

        Serializer<Table> serializer = MarkdownTableSerializer.create();

        String output = serializer.serialize(table);

        Assertions.assertEquals(
                """
                        | Score |       1 |       2 |  # |
                        |------:|--------:|--------:|---:|
                        |    22 |       * |       * |  1 |
                        |       |     D11 |       - |  1 |
                        |    23 |       * |       * | 38 |
                        |       |     D11 |       1 |  2 |
                        |       |  20/D10 |       3 |  4 |
                        |       |      19 |   4/ D2 |  4 |
                        |       |  18/ D9 |       5 |  4 |
                        |       |      17 |   6/ D3 |  4 |
                        |       |  16/ D8 |       7 |  4 |
                        |       |      15 |   8/ D4 |  4 |
                        |       |  14/ D7 |       9 |  4 |
                        |       |      13 |  10/ D5 |  4 |
                        |       |  12/ D6 |      11 |  4 |
                        """,
                output);
    }

    @ParameterizedTest
    @EnumSource(TableType.class)
    void getTheTableStart(TableType tableType) {
        BoardType boardType = BoardType.LONDON;
        CheckType checkInType = CheckType.ANY;
        CheckType checkoutType = CheckType.DOUBLE;
        FinderType finderType = FinderType.DESCENDING;

        TableGenerator tableGenerator = TableGeneratorFactory.create(
                tableType, boardType, checkInType, checkoutType, finderType
        );

        int minScore = 2;
        int maxScore = 2;

        Table table = tableGenerator.generate(minScore, maxScore);

        Serializer<Table> serializer = MarkdownTableSerializer.create();

        String output = serializer.serialize(table);

        Assertions.assertTrue(output.startsWith("| Score | "));
    }
}
