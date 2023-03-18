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
 * {@code Table} objects to Markdown.
 * <p>
 * Relevant design patterns: strategy, immutable object, static factory method.
 *
 * @author Maurits Silvis
 * @since 0.1.0
 */
final class MarkdownTableSerializer implements Serializer<Table> {
    private MarkdownTableSerializer() {
    }

    /**
     * Returns a new {@code MarkdownTableSerializer}.
     *
     * @return a new {@code MarkdownTableSerializer}
     */
    public static Serializer<Table> create() {
        return new MarkdownTableSerializer();
    }

    @Override
    public String serialize(Table object) {
        return new MarkdownTablePrinter(object).print();
    }

    private static class MarkdownTablePrinter extends TablePrinter {
        private final StringBuilder stringBuilder = new StringBuilder();
        private final String scoreFormat;
        private final String throwFormat;
        private final String fieldFormat;
        private final String multiplicityFormat;
        private final int numThrows;

        MarkdownTablePrinter(Table table) {
            super(table);

            int throwWidth = (getFieldWidth() + 3) * getThrowSize() - 3;

            scoreFormat = "%1$" + Math.max(getScoreWidth(), "Score".length()) + "s";
            throwFormat = "%1$" + throwWidth + "s";
            fieldFormat = "%1$" + getFieldWidth() + "s";

            int multiplicityWidth = getMultiplicityWidth();
            int formattedWidth = multiplicityWidth + (multiplicityWidth - 1) / 3;
            multiplicityFormat = "%1$" + Math.max(formattedWidth, "#".length()) + "s";

            numThrows = getNumThrows();
        }

        @Override
        void startTable() {
            stringBuilder.append("| ")
                    .append(scoreFormat.formatted("Score"))
                    .append(" ");

            IntStream.range(0, numThrows)
                    .mapToObj(i -> throwFormat.formatted(i + 1))
                    .forEach(str -> stringBuilder.append("| ").append(str).append(" "));

            stringBuilder.append("| ")
                    .append(multiplicityFormat.formatted("#"))
                    .append(" |\n");

            stringBuilder.append("|-")
                    .append(scoreFormat.formatted("").replace(' ', '-'))
                    .append(":");

            IntStream.range(0, numThrows)
                    .mapToObj(i -> throwFormat.formatted("").replace(' ', '-'))
                    .forEach(str -> stringBuilder.append("|-").append(str).append(":"));

            stringBuilder.append("|-")
                    .append(multiplicityFormat.formatted("").replace(' ', '-'))
                    .append(":|\n");
        }

        @Override
        void endTable() {
        }

        @Override
        void startScore(int score, int numCheckouts) {
            stringBuilder.append("| ")
                    .append(scoreFormat.formatted(score))
                    .append(' ');

            IntStream.range(0, numThrows)
                    .mapToObj(i -> throwFormat.formatted("*"))
                    .forEach(str -> stringBuilder.append("| ").append(str).append(' '));
        }

        @Override
        void endScore() {
        }

        @Override
        void separateScore() {
        }

        @Override
        void startMultiplicity() {
            stringBuilder.append("| ");
        }

        @Override
        void addMultiplicity(long multiplicity) {
            String multiplicityStr = String.format(Locale.US, "%,d", multiplicity);
            stringBuilder.append(multiplicityFormat.formatted(multiplicityStr));
        }

        @Override
        void endMultiplicity() {
            stringBuilder.append(" |\n");
        }

        @Override
        void startCheckouts() {
        }

        @Override
        void endCheckouts() {
        }

        @Override
        void startCheckout() {
        }

        @Override
        void endCheckout() {
            stringBuilder.append("|\n");
        }

        @Override
        void separateCheckout() {
        }

        @Override
        void startCheckoutScore() {
            stringBuilder.append("| ");
        }

        @Override
        void addCheckoutScore(int score) {
            stringBuilder.append(scoreFormat.formatted(""));
        }

        @Override
        void endCheckoutScore() {
            stringBuilder.append(' ');
        }

        @Override
        void startThrows() {
        }

        @Override
        void endThrows() {
        }

        @Override
        void startThrow() {
            stringBuilder.append("| ");
        }

        @Override
        void endThrow() {
            stringBuilder.append(" ");
        }

        @Override
        void separateThrow() {
        }

        @Override
        void addEmptyThrowAfter() {
            stringBuilder.append("| ")
                    .append(throwFormat.formatted("-"))
                    .append(' ');
        }

        @Override
        void addEmptyFieldBefore() {
            addField("");
            stringBuilder.append("   ");
        }

        @Override
        void startField() {
        }

        @Override
        void addField(String name) {
            stringBuilder.append(fieldFormat.formatted(name));
        }

        @Override
        void endField() {
        }

        @Override
        void separateField() {
            stringBuilder.append(" / ");
        }

        @Override
        void startCheckoutMultiplicity() {
            startMultiplicity();
        }

        @Override
        void addCheckoutMultiplicity(long multiplicity) {
            addMultiplicity(multiplicity);
        }

        @Override
        void endCheckoutMultiplicity() {
            stringBuilder.append(' ');
        }

        @Override
        String getString() {
            return stringBuilder.toString();
        }
    }
}
