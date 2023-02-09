/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.output;

import nl.mauritssilvis.darts.java.output.Serializer;
import nl.mauritssilvis.darts.java.tables.Table;

import java.util.stream.IntStream;

/**
 * An implementation of the generic {@code Serializer} interface that serializes
 * {@code Table} objects to HTML.
 * <p>
 * Relevant design patterns: strategy, immutable object, static factory method.
 */
public final class HtmlTableSerializer implements Serializer<Table> {
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

            int throwWidth = (getFieldWidth() + 23) * getThrowSize();
            int scoreSizeWidth = String.valueOf(getNumCheckouts() + 1).length();

            preScoreFormat = "%1$-" + (scoreSizeWidth + 28) + "s";
            scoreFormat = "%1$" + Math.max(getScoreWidth(), "Score".length()) + "s";
            throwFormat = "%1$" + throwWidth + "s";
            fieldFormat = "%1$" + getFieldWidth() + "s";
            emptyFieldFormat = "%1$" + (throwWidth / getThrowSize()) + "s";
            multiplicityFormat = "%1$" + getMultiplicityWidth() + "s";

            numThrows = getNumThrows();
        }

        @Override
        void startTable() {
            stringBuilder.append("<table>\n")
                    .append("  <tr class=\"h\"><th>")
                    .append(String.format(preScoreFormat, ""))
                    .append(String.format(scoreFormat, "Score"))
                    .append("</th>");

            IntStream.range(0, numThrows)
                    .mapToObj(i -> String.format(throwFormat, i + 1))
                    .forEach(str -> stringBuilder.append("<th class=\"t\">").append(str).append("</th>"));

            stringBuilder.append("<th class=\"m\">")
                    .append(String.format(multiplicityFormat, "#"))
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
                    .append(String.format(preScoreFormat, preScore))
                    .append(String.format(scoreFormat, score))
                    .append("</th>");

            String starField = "<span class=\"e\">" + String.format(fieldFormat, "*") + "</span>";

            IntStream.range(0, numThrows)
                    .mapToObj(i -> String.format(throwFormat, starField))
                    .forEach(str -> stringBuilder.append("<td class=\"t\">").append(str).append("</td>"));
        }

        @Override
        void endScore() {
        }

        @Override
        void separateScore() {
        }

        @Override
        void endLastScore() {
        }

        @Override
        void startMultiplicity() {
            stringBuilder.append("<td class=\"m\">");
        }

        @Override
        void addMultiplicity(long multiplicity) {
            stringBuilder.append(String.format(multiplicityFormat, multiplicity));
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
        void endLastCheckout() {
        }

        @Override
        void startCheckoutScore() {
            stringBuilder.append("    ")
                    .append(String.format(preScoreFormat, ""));
        }

        @Override
        void addCheckoutScore(int score) {
            stringBuilder.append(String.format(scoreFormat, ""));
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
        void endLastThrow() {
        }

        @Override
        void addEmptyThrowAfter() {
            startThrow();
            String noField = "<span class=\"n\">" + String.format(fieldFormat, "-") + "</span>";
            stringBuilder.append(String.format(throwFormat, noField));
            endThrow();
        }

        @Override
        void addEmptyFieldBefore() {
            stringBuilder.append(String.format(emptyFieldFormat, ""));
        }

        @Override
        void startField() {
            stringBuilder.append("<span class=\"f\">");
        }

        @Override
        void addField(String name) {
            stringBuilder.append(String.format(fieldFormat, name));
        }

        @Override
        void endField() {
            stringBuilder.append("</span>");
        }

        @Override
        void separateField() {
        }

        @Override
        void endLastField() {
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
