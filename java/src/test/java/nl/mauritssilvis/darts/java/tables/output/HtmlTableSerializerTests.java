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
                                  <tr class="h"><th>Score</th><th class="m">#</th></tr>
                                </table>
                                """
                ),
                Arguments.of(
                        0,
                        ThrowMode.FIXED,
                        """
                                <table>
                                  <tr class="h"><th>Score</th><th class="m">#</th></tr>
                                </table>
                                """
                ),
                Arguments.of(
                        1,
                        ThrowMode.OPTIMAL,
                        """
                                <table>
                                  <tr class="h"><th>Score</th><th class="t">1</th><th class="m">#</th></tr>
                                </table>
                                """
                ),
                Arguments.of(
                        2,
                        ThrowMode.FIXED,
                        """
                                <table>
                                  <tr class="h"><th>Score</th><th class="t">1</th><th class="t">2</th><th class="m">#</th></tr>
                                </table>
                                """
                )
        );
    }
}
