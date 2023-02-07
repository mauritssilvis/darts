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
        BoardType boardType = BoardType.LONDON;
        Board board = BoardFactory.create(boardType);
        Serializer<Board> serializer = HtmlBoardSerializer.create();

        String output = serializer.serialize(board);

        Assertions.assertEquals(
                """
                        <table>
                          <tr><th class="s">Score</th><th class="t"><span class="f">   </span><span class="f">  1</span></th><th class="t"><span class="f">     2 </span></th><th class="m"> #</th></tr>
                          <tr><td class="s">   22</td><td class="t"><span class="f">   </span><span class="f">  *</span></td><td class="t"><span class="f">     * </span></td><td class="m"> 1</td></tr>
                          <tr><td class="s">     </td><td class="t"><span class="f">   </span><span class="f">D11</span></td><td class="t"><span class="f">     - </span></td><td class="m"> 1</td></tr>
                          <tr><td class="s">   23</td><td class="t"><span class="f">   </span><span class="f">  *</span></td><td class="t"><span class="f">     * </span></td><td class="m">38</td></tr>
                          <tr><td class="s">     </td><td class="t"><span class="f">   </span><span class="f">D11</span></td><td class="t"><span class="f">     1 </span></td><td class="m"> 2</td></tr>
                          <tr><td class="s">     </td><td class="t"><span class="f"> 20</span><span class="f">D10</span></td><td class="t"><span class="f">     3 </span></td><td class="m"> 4</td></tr>
                          <tr><td class="s">     </td><td class="t"><span class="f">   </span><span class="f"> 19</span></td><td class="t"><span class="f"> 4/ D2 </span></td><td class="m"> 4</td></tr>
                          <tr><td class="s">     </td><td class="t"><span class="f"> 18</span><span class="f"> D9</span></td><td class="t"><span class="f">     5 </span></td><td class="m"> 4</td></tr>
                          <tr><td class="s">     </td><td class="t"><span class="f">   </span><span class="f"> 17</span></td><td class="t"><span class="f"> 6/ D3 </span></td><td class="m"> 4</td></tr>
                          <tr><td class="s">     </td><td class="t"><span class="f"> 16</span><span class="f"> D8</span></td><td class="t"><span class="f">     7 </span></td><td class="m"> 4</td></tr>
                          <tr><td class="s">     </td><td class="t"><span class="f">   </span><span class="f"> 15</span></td><td class="t"><span class="f"> 8/ D4 </span></td><td class="m"> 4</td></tr>
                          <tr><td class="s">     </td><td class="t"><span class="f"> 14</span><span class="f"> D7</span></td><td class="t"><span class="f">     9 </span></td><td class="m"> 4</td></tr>
                          <tr><td class="s">     </td><td class="t"><span class="f">   </span><span class="f"> 13</span></td><td class="t"><span class="f">10/ D5 </span></td><td class="m"> 4</td></tr>
                          <tr><td class="s">     </td><td class="t"><span class="f"> 12</span><span class="f"> D6</span></td><td class="t"><span class="f">    11 </span></td><td class="m"> 4</td></tr>
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
