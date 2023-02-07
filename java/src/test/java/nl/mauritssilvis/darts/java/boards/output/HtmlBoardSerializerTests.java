/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.boards.output;

import nl.mauritssilvis.darts.java.boards.Board;
import nl.mauritssilvis.darts.java.boards.factory.BoardFactory;
import nl.mauritssilvis.darts.java.output.Serializer;
import nl.mauritssilvis.darts.java.settings.BoardType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class HtmlBoardSerializerTests {
    @Test
    void getAnHtmlBoard() {
        BoardType boardType = BoardType.YORKSHIRE;
        Board board = BoardFactory.create(boardType);
        Serializer<Board> serializer = HtmlBoardSerializer.create();

        String output = serializer.serialize(board);

        Assertions.assertEquals(
                """
                        <table>
                          <tr><th>  S</th><th>  D</th></tr>
                          <tr><td>  1</td><td> D1</td></tr>
                          <tr><td>  2</td><td> D2</td></tr>
                          <tr><td>  3</td><td> D3</td></tr>
                          <tr><td>  4</td><td> D4</td></tr>
                          <tr><td>  5</td><td> D5</td></tr>
                          <tr><td>  6</td><td> D6</td></tr>
                          <tr><td>  7</td><td> D7</td></tr>
                          <tr><td>  8</td><td> D8</td></tr>
                          <tr><td>  9</td><td> D9</td></tr>
                          <tr><td> 10</td><td>D10</td></tr>
                          <tr><td> 11</td><td>D11</td></tr>
                          <tr><td> 12</td><td>D12</td></tr>
                          <tr><td> 13</td><td>D13</td></tr>
                          <tr><td> 14</td><td>D14</td></tr>
                          <tr><td> 15</td><td>D15</td></tr>
                          <tr><td> 16</td><td>D16</td></tr>
                          <tr><td> 17</td><td>D17</td></tr>
                          <tr><td> 18</td><td>D18</td></tr>
                          <tr><td> 19</td><td>D19</td></tr>
                          <tr><td> 20</td><td>D20</td></tr>
                          <tr><td>  -</td><td>D25</td></tr>
                        </table>
                        """,
                output);
    }

    @ParameterizedTest
    @EnumSource(BoardType.class)
    void getTheBoardStart(BoardType boardType) {
        Board board = BoardFactory.create(boardType);
        Serializer<Board> serializer = HtmlBoardSerializer.create();

        String output = serializer.serialize(board);

        Assertions.assertTrue(output.startsWith("<table>\n  <tr><th> "));
    }
}
