/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.output;

import nl.mauritssilvis.darts.java.output.Formatter;
import nl.mauritssilvis.darts.java.output.Serializer;
import nl.mauritssilvis.darts.java.output.pretty.PrettyFormatter;
import nl.mauritssilvis.darts.java.tables.Table;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * An implementation of the generic {@code Serializer} interface that serializes
 * {@code Table} objects to JSON.
 * <p>
 * Relevant design patterns: strategy, immutable object, static factory method.
 */
public final class JsonTableSerializer implements Serializer<Table> {
    private JsonTableSerializer() {
    }

    /**
     * Returns a new {@code JsonTableSerializer}.
     *
     * @return a new {@code JsonTableSerializer}
     */
    public static Serializer<Table> create() {
        return new JsonTableSerializer();
    }

    @Override
    public String serialize(Table object) {
        return new JsonTablePrinter(object).print();
    }

    private static class JsonTablePrinter extends TablePrinter {
        private final StringBuilder stringBuilder = new StringBuilder();
        private final Formatter formatter;

        JsonTablePrinter(Table table) {
            super(table);

            int indentationSize = 4;
            Collection<Character> brackets = List.of('{', '[', '(');
            Collection<Character> delimiters = Collections.singleton(',');

            formatter = PrettyFormatter.of(brackets, delimiters, indentationSize);
        }

        @Override
        void startTable() {
            stringBuilder.append('{');
        }

        @Override
        void endTable() {
            stringBuilder.append("}\n");
        }

        @Override
        void startScore(int score, int numCheckouts) {
            stringBuilder.append('"')
                    .append(score)
                    .append("\": {");
        }

        @Override
        void endScore() {
            stringBuilder.append('}');
        }

        @Override
        void separateScore() {
            stringBuilder.append(',');
        }

        @Override
        void endLastScore() {
        }

        @Override
        void startMultiplicity() {
            stringBuilder.append("\"multiplicity\": ");
        }

        @Override
        void addMultiplicity(long multiplicity) {
            stringBuilder.append(multiplicity);
        }

        @Override
        void endMultiplicity() {
            stringBuilder.append(',');
        }

        @Override
        void startCheckouts() {
            stringBuilder.append("\"checkouts\": [");
        }

        @Override
        void endCheckouts() {
            stringBuilder.append(']');
        }

        @Override
        void startCheckout() {
            stringBuilder.append('{');
        }

        @Override
        void endCheckout() {
            stringBuilder.append('}');
        }

        @Override
        void separateCheckout() {
            stringBuilder.append(',');
        }

        @Override
        void endLastCheckout() {
        }

        @Override
        void startCheckoutScore() {
        }

        @Override
        void addCheckoutScore(int score) {
        }

        @Override
        void endCheckoutScore() {
        }

        @Override
        void startThrows() {
            stringBuilder.append("\"throws\": [");
        }

        @Override
        void endThrows() {
            stringBuilder.append("],");
        }

        @Override
        void startThrow() {
            stringBuilder.append('[');
        }

        @Override
        void endThrow() {
            stringBuilder.append(']');
        }

        @Override
        void separateThrow() {
            stringBuilder.append(',');
        }

        @Override
        void endLastThrow() {
        }

        @Override
        void addEmptyThrowAfter() {
        }

        @Override
        void addEmptyFieldBefore() {
        }

        @Override
        void startField() {
            stringBuilder.append('"');
        }

        @Override
        void addField(String name) {
            stringBuilder.append(name);
        }

        @Override
        void endField() {
            stringBuilder.append('"');
        }

        @Override
        void separateField() {
            stringBuilder.append(',');
        }

        @Override
        void endLastField() {
        }

        @Override
        void startCheckoutMultiplicity() {
            stringBuilder.append("\"multiplicity\": ");
        }

        @Override
        void addCheckoutMultiplicity(long multiplicity) {
            stringBuilder.append(multiplicity);
        }

        @Override
        void endCheckoutMultiplicity() {
        }

        @Override
        String getString() {
            return formatter.format(stringBuilder.toString());
        }
    }
}
