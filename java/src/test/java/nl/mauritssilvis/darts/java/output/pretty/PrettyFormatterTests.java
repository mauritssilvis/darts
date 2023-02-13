/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.output.pretty;

import nl.mauritssilvis.darts.java.output.Formatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

class PrettyFormatterTests {
    @Test
    void insertNewlinesAndIndentationAroundBrackets() {
        Collection<Character> openingBrackets = List.of('{', '[');
        Collection<Character> closingBrackets = Collections.emptyList();
        Collection<Character> delimiters = Collections.emptyList();
        int indentationSize = 2;

        Formatter formatter = PrettyFormatter.of(openingBrackets, closingBrackets, delimiters, indentationSize);
        String input = "ab{cde[fghi]}";

        String output = formatter.format(input);

        Assertions.assertEquals("ab{\n  cde[\n    fghi\n  ]\n}", output);
    }

    @Test
    void insertANewlineAfterDelimiters() {
        Collection<Character> openingBrackets = Collections.emptyList();
        Collection<Character> closingBrackets = Collections.emptyList();
        Collection<Character> delimiters = List.of(',', ';');
        int indentationSize = 2;

        Formatter formatter = PrettyFormatter.of(openingBrackets, closingBrackets, delimiters, indentationSize);
        String input = "a,bc,def";

        String output = formatter.format(input);

        Assertions.assertEquals("a,\nbc,\ndef", output);
    }

    @Test
    void doNotAcceptMoreOpeningThanClosingBrackets() {
        Collection<Character> openingBrackets = List.of('{', '[');
        Collection<Character> closingBrackets = List.of(']', '}');
        Collection<Character> delimiters = Collections.emptyList();
        int indentationSize = 3;

        Formatter formatter = PrettyFormatter.of(openingBrackets, closingBrackets, delimiters, indentationSize);
        String input = "{[";

        Assertions.assertThrows(IllegalArgumentException.class, () -> formatter.format(input));
    }

    @Test
    void doNotAcceptMoreClosingThanOpeningBrackets() {
        Collection<Character> openingBrackets = List.of('{', '[');
        Collection<Character> closingBrackets = List.of(']', '}');
        Collection<Character> delimiters = Collections.emptyList();
        int indentationSize = 3;

        Formatter formatter = PrettyFormatter.of(openingBrackets, closingBrackets, delimiters, indentationSize);
        String input = "{[],[]]}}";

        Assertions.assertThrows(IllegalArgumentException.class, () -> formatter.format(input));
    }

    @Test
    void doNotAcceptMismatchingBrackets() {
        Collection<Character> openingBrackets = List.of('{', '(');
        Collection<Character> closingBrackets = List.of(')', '}');
        Collection<Character> delimiters = Collections.emptyList();
        int indentationSize = 2;

        Formatter formatter = PrettyFormatter.of(openingBrackets, closingBrackets, delimiters, indentationSize);
        String input = "{(}";

        Assertions.assertThrows(IllegalArgumentException.class, () -> formatter.format(input));
    }
}
