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

class HtmlTableSerializerTests {
    @Test
    void getAnHtmlTable() {
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

        Serializer<Table> serializer = HtmlTableSerializer.create();

        String output = serializer.serialize(table);

        Assertions.assertEquals(
                """
                        <table>
                          <tr><th class="s">Score</th><th class="t">                                                   1</th><th class="t">                                                   2</th><th class="m"> #</th></tr>
                          <tr><td class="s">   22</td><td class="t">                                                   *</td><td class="t">                                                   *</td><td class="m"> 1</td></tr>
                          <tr><td class="s">     </td><td class="t">                          <span class="f">D11</span></td><td class="t">                                                   -</td><td class="m"> 1</td></tr>
                          <tr><td class="s">   23</td><td class="t">                                                   *</td><td class="t">                                                   *</td><td class="m">38</td></tr>
                          <tr><td class="s">     </td><td class="t">                          <span class="f">D11</span></td><td class="t">                          <span class="f">  1</span></td><td class="m"> 2</td></tr>
                          <tr><td class="s">     </td><td class="t"><span class="f"> 20</span><span class="f">D10</span></td><td class="t">                          <span class="f">  3</span></td><td class="m"> 4</td></tr>
                          <tr><td class="s">     </td><td class="t">                          <span class="f"> 19</span></td><td class="t"><span class="f">  4</span><span class="f"> D2</span></td><td class="m"> 4</td></tr>
                          <tr><td class="s">     </td><td class="t"><span class="f"> 18</span><span class="f"> D9</span></td><td class="t">                          <span class="f">  5</span></td><td class="m"> 4</td></tr>
                          <tr><td class="s">     </td><td class="t">                          <span class="f"> 17</span></td><td class="t"><span class="f">  6</span><span class="f"> D3</span></td><td class="m"> 4</td></tr>
                          <tr><td class="s">     </td><td class="t"><span class="f"> 16</span><span class="f"> D8</span></td><td class="t">                          <span class="f">  7</span></td><td class="m"> 4</td></tr>
                          <tr><td class="s">     </td><td class="t">                          <span class="f"> 15</span></td><td class="t"><span class="f">  8</span><span class="f"> D4</span></td><td class="m"> 4</td></tr>
                          <tr><td class="s">     </td><td class="t"><span class="f"> 14</span><span class="f"> D7</span></td><td class="t">                          <span class="f">  9</span></td><td class="m"> 4</td></tr>
                          <tr><td class="s">     </td><td class="t">                          <span class="f"> 13</span></td><td class="t"><span class="f"> 10</span><span class="f"> D5</span></td><td class="m"> 4</td></tr>
                          <tr><td class="s">     </td><td class="t"><span class="f"> 12</span><span class="f"> D6</span></td><td class="t">                          <span class="f"> 11</span></td><td class="m"> 4</td></tr>
                        </table>
                        """,
                output);
    }

    @ParameterizedTest
    @EnumSource(TableType.class)
    void getTheTableStart(TableType tableType) {
        BoardType boardType = BoardType.QUADRO;
        CheckType checkInType = CheckType.MASTER;
        CheckType checkoutType = CheckType.MASTER;
        FinderType finderType = FinderType.CARTESIAN;

        TableGenerator tableGenerator = TableGeneratorFactory.create(
                tableType, boardType, checkInType, checkoutType, finderType
        );

        int minScore = 2;
        int maxScore = 2;

        Table table = tableGenerator.generate(minScore, maxScore);

        Serializer<Table> serializer = HtmlTableSerializer.create();

        String output = serializer.serialize(table);

        Assertions.assertTrue(output.startsWith("<table>\n  <tr><th class=\"s\">Score</th>"));
    }
}
