/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.output;

import nl.mauritssilvis.darts.java.output.Serializer;
import nl.mauritssilvis.darts.java.tables.Table;

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

        JsonTablePrinter(Table table) {
            super(table);
        }

        @Override
        void startTable() {
            stringBuilder.append("{\n");
        }

        @Override
        void endTable() {
            stringBuilder.append("}\n");
        }

        @Override
        void startScore(int score, int numCheckouts) {
            stringBuilder.append("    \"")
                    .append(score)
                    .append("\": {\n");
        }

        @Override
        void endScore() {
            stringBuilder.append("    }");
        }

        @Override
        void separateScore() {
            stringBuilder.append(",\n");
        }

        @Override
        void endLastScore() {
            stringBuilder.append('\n');
        }

        @Override
        void startMultiplicity() {
            stringBuilder.append("        \"multiplicity\": ");
        }

        @Override
        void addMultiplicity(long multiplicity) {
            stringBuilder.append(multiplicity);
        }

        @Override
        void endMultiplicity() {
            stringBuilder.append(",\n");
        }

        @Override
        void startCheckouts() {
            stringBuilder.append("        \"checkouts\": [\n");
        }

        @Override
        void endCheckouts() {
            stringBuilder.append("        ]\n");
        }

        @Override
        void startCheckout() {
            stringBuilder.append("            {\n");
        }

        @Override
        void endCheckout() {
            stringBuilder.append("            }");
        }

        @Override
        void separateCheckout() {
            stringBuilder.append(",\n");
        }

        @Override
        void endLastCheckout() {
            stringBuilder.append('\n');
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
            stringBuilder.append("                \"throws\": [\n");
        }

        @Override
        void endThrows() {
            stringBuilder.append("                ],\n");
        }

        @Override
        void startThrow() {
            stringBuilder.append("                    [\n");
        }

        @Override
        void endThrow() {
            stringBuilder.append("                    ]");
        }

        @Override
        void separateThrow() {
            stringBuilder.append(",\n");
        }

        @Override
        void endLastThrow() {
            stringBuilder.append('\n');
        }

        @Override
        void addEmptyThrowAfter() {
        }

        @Override
        void addEmptyFieldBefore() {
        }

        @Override
        void startField() {
            stringBuilder.append("                        \"");
        }

        @Override
        void addField(String name) {
            stringBuilder.append(name);
        }

        @Override
        void endField() {
            stringBuilder.append("\"");
        }

        @Override
        void separateField() {
            stringBuilder.append(",\n");
        }

        @Override
        void endLastField() {
            stringBuilder.append('\n');
        }

        @Override
        void startCheckoutMultiplicity() {
            stringBuilder.append("                \"multiplicity\": ");
        }

        @Override
        void addCheckoutMultiplicity(long multiplicity) {
            stringBuilder.append(multiplicity);
        }

        @Override
        void endCheckoutMultiplicity() {
            stringBuilder.append('\n');
        }

        @Override
        String getString() {
            return stringBuilder.toString();
        }
    }
}
