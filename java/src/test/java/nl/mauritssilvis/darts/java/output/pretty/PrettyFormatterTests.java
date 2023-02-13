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
    void doNotAcceptUnsupportedBrackets() {
        Collection<Character> brackets = List.of('a');
        Collection<Character> delimiters = Collections.emptyList();
        int indentationSize = 4;

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> PrettyFormatter.of(brackets, delimiters, indentationSize)
        );
    }

    @Test
    void doNotAcceptMoreOpeningThanClosingBrackets() {
        Collection<Character> brackets = List.of('{', '[');
        Collection<Character> delimiters = Collections.emptyList();
        int indentationSize = 3;

        Formatter formatter = PrettyFormatter.of(brackets, delimiters, indentationSize);
        String input = "{[";

        Assertions.assertThrows(IllegalArgumentException.class, () -> formatter.format(input));
    }

    @Test
    void doNotAcceptMoreClosingThanOpeningBrackets() {
        Collection<Character> brackets = List.of('{', '[');
        Collection<Character> delimiters = Collections.emptyList();
        int indentationSize = 3;

        Formatter formatter = PrettyFormatter.of(brackets, delimiters, indentationSize);
        String input = "{[],[]]}}";

        Assertions.assertThrows(IllegalArgumentException.class, () -> formatter.format(input));
    }

    @Test
    void doNotAcceptMismatchingBrackets() {
        Collection<Character> brackets = List.of('{', '(');
        Collection<Character> delimiters = Collections.emptyList();
        int indentationSize = 2;

        Formatter formatter = PrettyFormatter.of(brackets, delimiters, indentationSize);
        String input = "{(}";

        Assertions.assertThrows(IllegalArgumentException.class, () -> formatter.format(input));
    }

    @Test
    void insertNewlinesAndIndentationAroundBrackets() {
        Collection<Character> brackets = List.of('{', '[');
        Collection<Character> delimiters = Collections.emptyList();
        int indentationSize = 2;

        Formatter formatter = PrettyFormatter.of(brackets, delimiters, indentationSize);
        String input = "ab{cde[fghi]}";

        String output = formatter.format(input);

        Assertions.assertEquals("ab{\n  cde[\n    fghi\n  ]\n}", output);
    }

    @Test
    void insertANewlineAfterDelimiters() {
        Collection<Character> brackets = Collections.emptyList();
        Collection<Character> delimiters = List.of(',', ';');
        int indentationSize = 2;

        Formatter formatter = PrettyFormatter.of(brackets, delimiters, indentationSize);
        String input = "a,bc,def";

        String output = formatter.format(input);

        Assertions.assertEquals("a,\nbc,\ndef", output);
    }

    @Test
    void processBracketsAndDelimiters() {
        Collection<Character> brackets = List.of('(');
        Collection<Character> delimiters = List.of(',', ';');
        int indentationSize = 4;

        Formatter formatter = PrettyFormatter.of(brackets, delimiters, indentationSize);
        String input = "A(B,C(D;E),F)";

        String output = formatter.format(input);

        Assertions.assertEquals("A(\n    B,\n    C(\n        D;\n        E\n    ),\n    F\n)", output);
    }

    @Test
    void avoidDoubleNewlinesWithDelimitedBrackets() {
        Collection<Character> brackets = List.of('(');
        Collection<Character> delimiters = List.of(',');
        int indentationSize = 2;

        Formatter formatter = PrettyFormatter.of(brackets, delimiters, indentationSize);
        String input = "(),()";

        String output = formatter.format(input);

        Assertions.assertEquals("(\n),\n(\n)", output);
    }

    @Test
    void avoidDoubleNewlinesWithBackToBackBrackets() {
        Collection<Character> brackets = List.of('(');
        Collection<Character> delimiters = Collections.emptyList();
        int indentationSize = 2;

        Formatter formatter = PrettyFormatter.of(brackets, delimiters, indentationSize);
        String input = "()()";

        String output = formatter.format(input);

        Assertions.assertEquals("(\n)(\n)", output);
    }
}
