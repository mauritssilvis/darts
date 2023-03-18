/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.core.output;

import nl.mauritssilvis.darts.java.api.output.Formatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class PrettyFormatterTests {
    @Test
    void storeIndependentBrackets() {
        Collection<Character> brackets = new ArrayList<>(List.of('('));
        Collection<Character> delimiters = Collections.emptyList();
        int indentationSize = 2;

        Formatter formatter = PrettyFormatter.of(brackets, delimiters, indentationSize);

        brackets.clear();

        String input = "()";
        String output = formatter.format(input);

        Assertions.assertEquals("(\n)", output);
    }

    @Test
    void storeIndependentDelimiters() {
        Collection<Character> brackets = Collections.emptyList();
        Collection<Character> delimiters = new ArrayList<>(List.of(','));
        int indentationSize = 2;

        Formatter formatter = PrettyFormatter.of(brackets, delimiters, indentationSize);

        delimiters.clear();

        String input = "a,b";
        String output = formatter.format(input);

        Assertions.assertEquals("a,\nb", output);
    }

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

    @Test
    void getEqualFormatters() {
        Collection<Character> brackets1 = List.of('{', '[');
        Collection<Character> delimiters1 = List.of(';');
        int indentationSize1 = 2;

        Formatter formatter1 = PrettyFormatter.of(brackets1, delimiters1, indentationSize1);

        Collection<Character> brackets2 = List.of('{', '[');
        Collection<Character> delimiters2 = List.of(';');
        int indentationSize2 = 2;

        Formatter formatter2 = PrettyFormatter.of(brackets2, delimiters2, indentationSize2);

        Assertions.assertEquals(formatter1, formatter2);
    }

    @Test
    void getUnequalFormatters() {
        Collection<Character> brackets1 = List.of('[');
        Collection<Character> delimiters1 = List.of(',', ';');
        int indentationSize1 = 4;

        Formatter formatter1 = PrettyFormatter.of(brackets1, delimiters1, indentationSize1);

        Collection<Character> brackets2 = List.of('{', '[');
        Collection<Character> delimiters2 = List.of(';');
        int indentationSize2 = 8;

        Formatter formatter2 = PrettyFormatter.of(brackets2, delimiters2, indentationSize2);

        Assertions.assertNotEquals(formatter1, formatter2);
    }

    @Test
    void getUnequalFormattersForDifferentBrackets() {
        Collection<Character> delimiters = List.of(',', ';');
        int indentationSize = 2;

        Collection<Character> brackets1 = List.of('[');
        Formatter formatter1 = PrettyFormatter.of(brackets1, delimiters, indentationSize);

        Collection<Character> brackets2 = List.of('{', '[');
        Formatter formatter2 = PrettyFormatter.of(brackets2, delimiters, indentationSize);

        Assertions.assertNotEquals(formatter1, formatter2);
    }

    @Test
    void getUnequalFormattersForDifferentDelimiters() {
        Collection<Character> brackets = List.of('(');
        int indentationSize = 2;

        Collection<Character> delimiters1 = List.of(',', ';');
        Formatter formatter1 = PrettyFormatter.of(brackets, delimiters1, indentationSize);

        Collection<Character> delimiters2 = List.of(',');
        Formatter formatter2 = PrettyFormatter.of(brackets, delimiters2, indentationSize);

        Assertions.assertNotEquals(formatter1, formatter2);
    }

    @Test
    void getUnequalFormattersForDifferentIndentationSizes() {
        Collection<Character> brackets = List.of('(');
        Collection<Character> delimiters = List.of(',');

        int indentationSize1 = 2;
        Formatter formatter1 = PrettyFormatter.of(brackets, delimiters, indentationSize1);

        int indentationSize2 = 4;
        Formatter formatter2 = PrettyFormatter.of(brackets, delimiters, indentationSize2);

        Assertions.assertNotEquals(formatter1, formatter2);
    }

    @Test
    void convertToAString() {
        Collection<Character> brackets = Collections.emptyList();
        Collection<Character> delimiters = Collections.emptyList();
        int indentationSize = 4;

        Formatter formatter = PrettyFormatter.of(brackets, delimiters, indentationSize);
        String str = formatter.toString();

        Assertions.assertAll(
                () -> Assertions.assertTrue(str.startsWith(formatter.getClass().getSimpleName())),
                () -> Assertions.assertTrue(str.contains("openingBrackets")),
                () -> Assertions.assertTrue(str.contains("closingBrackets")),
                () -> Assertions.assertTrue(str.contains("delimiters")),
                () -> Assertions.assertTrue(str.contains("indentationSize"))
        );
    }
}
