/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.output;

import nl.mauritssilvis.darts.java.output.Serializer;
import nl.mauritssilvis.darts.java.tables.Table;

import java.util.stream.IntStream;

/**
 * An implementation of the generic {@code Serializer} interface that serializes
 * {@code Table} objects to Markdown.
 * <p>
 * Relevant design patterns: strategy, immutable object, static factory method.
 */
public final class MarkdownTableSerializer implements Serializer<Table> {
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

            int throwWidth = (getFieldWidth() + 1) * getThrowSize() - 1;

            scoreFormat = "%1$" + Math.max(getScoreWidth(), "Score".length()) + "s";
            throwFormat = "%1$" + throwWidth + "s";
            fieldFormat = "%1$" + getFieldWidth() + "s";
            multiplicityFormat = "%1$" + getMultiplicityWidth() + "s";

            numThrows = getNumThrows();
        }

        @Override
        void startTable() {
            stringBuilder.append("| ")
                    .append(String.format(scoreFormat, "Score"))
                    .append(" ");

            IntStream.range(0, numThrows)
                    .mapToObj(i -> String.format(throwFormat, i + 1))
                    .forEach(str -> stringBuilder.append("| ").append(str).append(" "));

            stringBuilder.append("| ")
                    .append(String.format(multiplicityFormat, "#"))
                    .append(" |\n");

            stringBuilder.append("|-")
                    .append(String.format(scoreFormat, "").replace(' ', '-'))
                    .append(":");

            IntStream.range(0, numThrows)
                    .mapToObj(i -> String.format(throwFormat, "").replace(' ', '-'))
                    .forEach(str -> stringBuilder.append("|-").append(str).append(":"));

            stringBuilder.append("|-")
                    .append(String.format(multiplicityFormat, "").replace(' ', '-'))
                    .append(":|\n");
        }

        @Override
        void endTable() {
        }

        @Override
        void startScore(int score) {
            stringBuilder.append("| ")
                    .append(String.format(scoreFormat, score))
                    .append(' ');

            IntStream.range(0, numThrows)
                    .mapToObj(i -> String.format(throwFormat, "*"))
                    .forEach(str -> stringBuilder.append("| ").append(str).append(' '));
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
            stringBuilder.append("| ");
        }

        @Override
        void addMultiplicity(long multiplicity) {
            stringBuilder.append(String.format(multiplicityFormat, multiplicity));
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
        }

        @Override
        void separateCheckout() {
        }

        @Override
        void endLastCheckout() {
        }

        @Override
        void startCheckoutScore() {
            stringBuilder.append("| ");
        }

        @Override
        void addCheckoutScore(int score) {
            stringBuilder.append(String.format(scoreFormat, ""));
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
        void addEmptyThrowBefore() {
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
        void endLastThrow() {
        }

        @Override
        void addEmptyThrowAfter() {
            stringBuilder.append("| ")
                    .append(String.format(throwFormat, "-"))
                    .append(' ');
        }

        @Override
        void addEmptyFieldBefore() {
            addField("");
            stringBuilder.append(' ');
        }

        @Override
        void startField() {
        }

        @Override
        void addField(String name) {
            stringBuilder.append(String.format(fieldFormat, name));
        }

        @Override
        void endField() {
        }

        @Override
        void separateField() {
            stringBuilder.append('/');
        }

        @Override
        void endLastField() {
        }

        @Override
        void addEmptyFieldAfter() {
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
            endMultiplicity();
        }

        @Override
        String getString() {
            return stringBuilder.toString();
        }
    }
}
