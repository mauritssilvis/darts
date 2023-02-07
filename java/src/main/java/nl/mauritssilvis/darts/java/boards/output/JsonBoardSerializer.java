/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.boards.output;

import nl.mauritssilvis.darts.java.boards.Board;
import nl.mauritssilvis.darts.java.boards.FieldType;
import nl.mauritssilvis.darts.java.output.Serializer;

/**
 * An implementation of the generic {@code Serializer} interface that serializes
 * {@code Board} objects to JSON.
 * <p>
 * Relevant design patterns: strategy, immutable object, static factory method.
 */
public final class JsonBoardSerializer implements Serializer<Board> {
    private JsonBoardSerializer() {
    }

    /**
     * Returns a new {@code JsonBoardSerializer}.
     *
     * @return a new {@code JsonBoardSerializer}
     */
    public static Serializer<Board> create() {
        return new JsonBoardSerializer();
    }

    @Override
    public String serialize(Board object) {
        return new JsonBoardPrinter(object).print();
    }

    private static class JsonBoardPrinter extends BoardPrinter {
        private final StringBuilder stringBuilder = new StringBuilder();

        JsonBoardPrinter(Board board) {
            super(board);
        }

        @Override
        void startBoard() {
            stringBuilder.append("{\n");
        }

        @Override
        void endBoard() {
            stringBuilder.append("}\n");
        }

        @Override
        void startType(FieldType fieldType) {
            String typeName = getTypeName(fieldType);

            stringBuilder.append("    \"")
                    .append(typeName)
                    .append("\": [\n");
        }

        @Override
        void endType() {
            stringBuilder.append("    ]");
        }

        @Override
        void separateType() {
            stringBuilder.append(",\n");
        }

        @Override
        void endLastType() {
            stringBuilder.append('\n');
        }

        @Override
        void startField() {
            stringBuilder.append("        \"");
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
            stringBuilder.append(",\n");
        }

        @Override
        void endLastField() {
            stringBuilder.append('\n');
        }

        @Override
        void startEmptyField() {
        }

        @Override
        void addEmptyField() {
        }

        @Override
        void endEmptyField() {
        }

        @Override
        String getString() {
            return stringBuilder.toString();
        }

        private static String getTypeName(FieldType fieldType) {
            String fullName = fieldType.toString();
            String shortName = fullName.split("\\.")[1];
            return shortName.toLowerCase() + "s";
        }
    }
}
