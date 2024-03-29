/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.core.tables.output;

import nl.mauritssilvis.darts.java.api.output.Serializer;
import nl.mauritssilvis.darts.java.api.tables.Table;

import java.util.Locale;
import java.util.stream.IntStream;

/**
 * An implementation of the generic {@code Serializer} interface that serializes
 * {@code Table} objects to HTML.
 * <p>
 * Relevant design patterns: strategy, immutable object, simple factory.
 *
 * @author Maurits Silvis
 * @since 0.5.0
 */
final class HtmlTableSerializer implements Serializer<Table> {
    private HtmlTableSerializer() {
    }

    /**
     * Returns a new {@code HtmlTableSerializer}.
     *
     * @return a new {@code HtmlTableSerializer}
     */
    public static Serializer<Table> create() {
        return new HtmlTableSerializer();
    }

    @Override
    public String serialize(Table object) {
        return new HtmlTablePrinter(object).print();
    }

    private static class HtmlTablePrinter extends TablePrinter {
        private final StringBuilder stringBuilder = new StringBuilder();
        private final String preScoreFormat;
        private final String scoreFormat;
        private final String throwFormat;
        private final String fieldFormat;
        private final String emptyFieldFormat;
        private final String multiplicityFormat;
        private final int numThrows;

        HtmlTablePrinter(Table table) {
            super(table);

            int scoreSizeWidth = String.valueOf(getNumCheckouts() + 1).length();
            int throwSize = getThrowSize();
            int fieldWidth = getFieldWidth();
            int throwWidth = (fieldWidth + 23) * throwSize;

            preScoreFormat = "%1$-" + (scoreSizeWidth + 28) + "s";
            scoreFormat = "%1$" + Math.max(getScoreWidth(), "Score".length()) + "s";
            throwFormat = "%1$" + throwWidth + "s";
            fieldFormat = "%1$" + (fieldWidth > 0 ? fieldWidth : 1) + "s";
            emptyFieldFormat = throwSize > 0 ? "%1$" + (throwWidth / throwSize) + "s" : "%1$1s";

            int multiplicityWidth = getMultiplicityWidth();
            int formattedWidth = multiplicityWidth + (multiplicityWidth - 1) / 3;
            multiplicityFormat = "%1$" + Math.max(formattedWidth, "#".length()) + "s";

            numThrows = getNumThrows();
        }

        @Override
        void startTable() {
            stringBuilder.append("<table>\n")
                    .append("  <tr class=\"h\"><th>")
                    .append(preScoreFormat.formatted(""))
                    .append(scoreFormat.formatted("Score"))
                    .append("</th>");

            IntStream.range(0, numThrows)
                    .mapToObj(i -> throwFormat.formatted(i + 1))
                    .forEach(str -> stringBuilder.append("<th class=\"t\">").append(str).append("</th>"));

            stringBuilder.append("<th class=\"m\">")
                    .append(multiplicityFormat.formatted("#"))
                    .append("</th></tr>\n");
        }

        @Override
        void endTable() {
            stringBuilder.append("</table>\n");
        }

        @Override
        void startScore(int score, int numCheckouts) {
            String preScore = "rowspan=\"" + (numCheckouts + 1) + "\" scope=\"rowgroup\">";

            stringBuilder.append("  <tr class=\"s\"><th ")
                    .append(preScoreFormat.formatted(preScore))
                    .append(scoreFormat.formatted(score))
                    .append("</th>");

            String starField = "<span class=\"e\">" + fieldFormat.formatted("*") + "</span>";

            IntStream.range(0, numThrows)
                    .mapToObj(i -> throwFormat.formatted(starField))
                    .forEach(str -> stringBuilder.append("<td class=\"t\">").append(str).append("</td>"));
        }

        @Override
        void endScore() {
        }

        @Override
        void separateScore() {
        }

        @Override
        void startMultiplicity() {
            stringBuilder.append("<td class=\"m\">");
        }

        @Override
        void addMultiplicity(long multiplicity) {
            String multiplicityStr = String.format(Locale.US, "%,d", multiplicity);
            stringBuilder.append(multiplicityFormat.formatted(multiplicityStr));
        }

        @Override
        void endMultiplicity() {
            stringBuilder.append("</td></tr>\n");
        }

        @Override
        void startCheckouts() {
        }

        @Override
        void endCheckouts() {
        }

        @Override
        void startCheckout() {
            stringBuilder.append("  <tr class=\"c\">");
        }

        @Override
        void endCheckout() {
            stringBuilder.append("</tr>\n");
        }

        @Override
        void separateCheckout() {
        }

        @Override
        void startCheckoutScore() {
            stringBuilder.append("    ")
                    .append(preScoreFormat.formatted(""));
        }

        @Override
        void addCheckoutScore(int score) {
            stringBuilder.append(scoreFormat.formatted(""));
        }

        @Override
        void endCheckoutScore() {
            stringBuilder.append("     ");
        }

        @Override
        void startThrows() {
        }

        @Override
        void endThrows() {
        }

        @Override
        void startThrow() {
            stringBuilder.append("<td class=\"t\">");
        }

        @Override
        void endThrow() {
            stringBuilder.append("</td>");
        }

        @Override
        void separateThrow() {
        }

        @Override
        void addEmptyThrowAfter() {
            startThrow();
            String noField = "<span class=\"n\">" + fieldFormat.formatted("-") + "</span>";
            stringBuilder.append(throwFormat.formatted(noField));
            endThrow();
        }

        @Override
        void addEmptyFieldBefore() {
            stringBuilder.append(emptyFieldFormat.formatted(""));
        }

        @Override
        void startField() {
            stringBuilder.append("<span class=\"f\">");
        }

        @Override
        void addField(String name) {
            stringBuilder.append(fieldFormat.formatted(name));
        }

        @Override
        void endField() {
            stringBuilder.append("</span>");
        }

        @Override
        void separateField() {
        }

        @Override
        void startCheckoutMultiplicity() {
            stringBuilder.append("<td class=\"m\">");
        }

        @Override
        void addCheckoutMultiplicity(long multiplicity) {
            addMultiplicity(multiplicity);
        }

        @Override
        void endCheckoutMultiplicity() {
            stringBuilder.append("</td>");
        }

        @Override
        String getString() {
            return stringBuilder.toString();
        }
    }
}
