/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.core.tables.output;

import nl.mauritssilvis.darts.java.api.output.Serializer;
import nl.mauritssilvis.darts.java.api.settings.*;
import nl.mauritssilvis.darts.java.api.tables.Table;
import nl.mauritssilvis.darts.java.api.tables.TableGenerator;
import nl.mauritssilvis.darts.java.core.settings.TableSettingsBuilder;
import nl.mauritssilvis.darts.java.core.tables.TableGeneratorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class HtmlTableSerializerTests {
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

        Serializer<Table> serializer = HtmlTableSerializer.create();

        String output = serializer.serialize(table);

        Assertions.assertEquals(
                """
                        <table>
                          <tr class="h"><th>                              Score</th><th class="t">                                                   1</th><th class="t">                                                   2</th><th class="m"> #</th></tr>
                          <tr class="s"><th rowspan="2" scope="rowgroup">    22</th><td class="t">                          <span class="e">  *</span></td><td class="t">                          <span class="e">  *</span></td><td class="m"> 1</td></tr>
                          <tr class="c">                                            <td class="t">                          <span class="f">D11</span></td><td class="t">                          <span class="n">  -</span></td><td class="m"> 1</td></tr>
                          <tr class="s"><th rowspan="11" scope="rowgroup">   23</th><td class="t">                          <span class="e">  *</span></td><td class="t">                          <span class="e">  *</span></td><td class="m">38</td></tr>
                          <tr class="c">                                            <td class="t">                          <span class="f">D11</span></td><td class="t">                          <span class="f">  1</span></td><td class="m"> 2</td></tr>
                          <tr class="c">                                            <td class="t"><span class="f"> 20</span><span class="f">D10</span></td><td class="t">                          <span class="f">  3</span></td><td class="m"> 4</td></tr>
                          <tr class="c">                                            <td class="t">                          <span class="f"> 19</span></td><td class="t"><span class="f">  4</span><span class="f"> D2</span></td><td class="m"> 4</td></tr>
                          <tr class="c">                                            <td class="t"><span class="f"> 18</span><span class="f"> D9</span></td><td class="t">                          <span class="f">  5</span></td><td class="m"> 4</td></tr>
                          <tr class="c">                                            <td class="t">                          <span class="f"> 17</span></td><td class="t"><span class="f">  6</span><span class="f"> D3</span></td><td class="m"> 4</td></tr>
                          <tr class="c">                                            <td class="t"><span class="f"> 16</span><span class="f"> D8</span></td><td class="t">                          <span class="f">  7</span></td><td class="m"> 4</td></tr>
                          <tr class="c">                                            <td class="t">                          <span class="f"> 15</span></td><td class="t"><span class="f">  8</span><span class="f"> D4</span></td><td class="m"> 4</td></tr>
                          <tr class="c">                                            <td class="t"><span class="f"> 14</span><span class="f"> D7</span></td><td class="t">                          <span class="f">  9</span></td><td class="m"> 4</td></tr>
                          <tr class="c">                                            <td class="t">                          <span class="f"> 13</span></td><td class="t"><span class="f"> 10</span><span class="f"> D5</span></td><td class="m"> 4</td></tr>
                          <tr class="c">                                            <td class="t"><span class="f"> 12</span><span class="f"> D6</span></td><td class="t">                          <span class="f"> 11</span></td><td class="m"> 4</td></tr>
                        </table>
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

        Serializer<Table> serializer = HtmlTableSerializer.create();

        String output = serializer.serialize(table);

        Assertions.assertTrue(output.startsWith("<table>\n  <tr class=\"h\"><th> "));
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

        Serializer<Table> serializer = HtmlTableSerializer.create();

        Assertions.assertEquals(output, serializer.serialize(table));
    }

    private static Stream<Arguments> withAnEmptySerializedTable() {
        return Stream.of(
                Arguments.of(
                        0,
                        ThrowMode.OPTIMAL,
                        """
                                <table>
                                  <tr class="h"><th>                             Score</th><th class="m">#</th></tr>
                                </table>
                                """
                ),
                Arguments.of(
                        0,
                        ThrowMode.FIXED,
                        """
                                <table>
                                  <tr class="h"><th>                             Score</th><th class="m">#</th></tr>
                                </table>
                                """
                ),
                Arguments.of(
                        1,
                        ThrowMode.OPTIMAL,
                        """
                                <table>
                                  <tr class="h"><th>                             Score</th><th class="t">                       1</th><th class="m">#</th></tr>
                                </table>
                                """
                ),
                Arguments.of(
                        2,
                        ThrowMode.FIXED,
                        """
                                <table>
                                  <tr class="h"><th>                             Score</th><th class="t">                       1</th><th class="t">                       2</th><th class="m">#</th></tr>
                                </table>
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

        Serializer<Table> serializer = HtmlTableSerializer.create();

        Assertions.assertEquals(output, serializer.serialize(table));
    }

    private static Stream<Arguments> withAnEmptySerializedRow() {
        return Stream.of(
                Arguments.of(
                        0,
                        ThrowMode.OPTIMAL,
                        """
                                <table>
                                  <tr class="h"><th>                             Score</th><th class="m">#</th></tr>
                                  <tr class="s"><th rowspan="1" scope="rowgroup">    0</th><td class="m">0</td></tr>
                                </table>
                                """
                ),
                Arguments.of(
                        0,
                        ThrowMode.FIXED,
                        """
                                <table>
                                  <tr class="h"><th>                             Score</th><th class="m">#</th></tr>
                                  <tr class="s"><th rowspan="1" scope="rowgroup">    0</th><td class="m">0</td></tr>
                                </table>
                                """
                ),
                Arguments.of(
                        2,
                        ThrowMode.OPTIMAL,
                        """
                                <table>
                                  <tr class="h"><th>                             Score</th><th class="t">                       1</th><th class="t">                       2</th><th class="m">#</th></tr>
                                  <tr class="s"><th rowspan="1" scope="rowgroup">    0</th><td class="t"><span class="e">*</span></td><td class="t"><span class="e">*</span></td><td class="m">0</td></tr>
                                </table>
                                """
                ),
                Arguments.of(
                        3,
                        ThrowMode.FIXED,
                        """
                                <table>
                                  <tr class="h"><th>                             Score</th><th class="t">                       1</th><th class="t">                       2</th><th class="t">                       3</th><th class="m">#</th></tr>
                                  <tr class="s"><th rowspan="1" scope="rowgroup">    0</th><td class="t"><span class="e">*</span></td><td class="t"><span class="e">*</span></td><td class="t"><span class="e">*</span></td><td class="m">0</td></tr>
                                </table>
                                """
                )
        );
    }

    @ParameterizedTest
    @MethodSource("withThousandSeparators")
    void getThousandSeparators(String output) {
        CheckMode checkoutMode = CheckMode.ANY;
        TableType tableType = TableType.ASCENDING;

        Settings settings = TableSettingsBuilder.create()
                .setCheckoutMode(checkoutMode)
                .setTableType(tableType)
                .build();

        TableGenerator tableGenerator = TableGeneratorFactory.create(tableType, settings);

        int score = 701;

        Table table = tableGenerator.generate(score, score);

        Serializer<Table> serializer = HtmlTableSerializer.create();

        Assertions.assertEquals(output, serializer.serialize(table));
    }

    private static Stream<Arguments> withThousandSeparators() {
        return Stream.of(
                Arguments.of(
                        """
                                <table>
                                  <tr class="h"><th>                             Score</th><th class="t">                         1</th><th class="t">                         2</th><th class="t">                         3</th><th class="t">                         4</th><th class="t">                         5</th><th class="t">                         6</th><th class="t">                         7</th><th class="t">                         8</th><th class="t">                         9</th><th class="t">                        10</th><th class="t">                        11</th><th class="t">                        12</th><th class="m">    #</th></tr>
                                  <tr class="s"><th rowspan="4" scope="rowgroup">  701</th><td class="t"><span class="e">  *</span></td><td class="t"><span class="e">  *</span></td><td class="t"><span class="e">  *</span></td><td class="t"><span class="e">  *</span></td><td class="t"><span class="e">  *</span></td><td class="t"><span class="e">  *</span></td><td class="t"><span class="e">  *</span></td><td class="t"><span class="e">  *</span></td><td class="t"><span class="e">  *</span></td><td class="t"><span class="e">  *</span></td><td class="t"><span class="e">  *</span></td><td class="t"><span class="e">  *</span></td><td class="m">3,432</td></tr>
                                  <tr class="c">                                           <td class="t"><span class="f">T20</span></td><td class="t"><span class="f">T20</span></td><td class="t"><span class="f">T20</span></td><td class="t"><span class="f">T20</span></td><td class="t"><span class="f">T20</span></td><td class="t"><span class="f">T20</span></td><td class="t"><span class="f">T20</span></td><td class="t"><span class="f">T20</span></td><td class="t"><span class="f">T20</span></td><td class="t"><span class="f">T20</span></td><td class="t"><span class="f">T17</span></td><td class="t"><span class="f">D25</span></td><td class="m">  132</td></tr>
                                  <tr class="c">                                           <td class="t"><span class="f">T20</span></td><td class="t"><span class="f">T20</span></td><td class="t"><span class="f">T20</span></td><td class="t"><span class="f">T20</span></td><td class="t"><span class="f">T20</span></td><td class="t"><span class="f">T20</span></td><td class="t"><span class="f">T20</span></td><td class="t"><span class="f">T20</span></td><td class="t"><span class="f">T20</span></td><td class="t"><span class="f">T19</span></td><td class="t"><span class="f">T18</span></td><td class="t"><span class="f">D25</span></td><td class="m">1,320</td></tr>
                                  <tr class="c">                                           <td class="t"><span class="f">T20</span></td><td class="t"><span class="f">T20</span></td><td class="t"><span class="f">T20</span></td><td class="t"><span class="f">T20</span></td><td class="t"><span class="f">T20</span></td><td class="t"><span class="f">T20</span></td><td class="t"><span class="f">T20</span></td><td class="t"><span class="f">T20</span></td><td class="t"><span class="f">T19</span></td><td class="t"><span class="f">T19</span></td><td class="t"><span class="f">T19</span></td><td class="t"><span class="f">D25</span></td><td class="m">1,980</td></tr>
                                </table>
                                """
                )
        );
    }
}
