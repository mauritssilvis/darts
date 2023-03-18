/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.boards.output;

import nl.mauritssilvis.darts.java.api.boards.Board;
import nl.mauritssilvis.darts.java.api.boards.FieldType;
import nl.mauritssilvis.darts.java.api.output.Formatter;
import nl.mauritssilvis.darts.java.api.output.Serializer;
import nl.mauritssilvis.darts.java.output.PrettyFormatter;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * An implementation of the generic {@code Serializer} interface that serializes
 * {@code Board} objects to JSON.
 * <p>
 * Relevant design patterns: strategy, immutable object, static factory method.
 *
 * @author Maurits Silvis
 * @since 0.1.0
 */
final class JsonBoardSerializer implements Serializer<Board> {
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
        private final Formatter formatter;

        JsonBoardPrinter(Board board) {
            super(board);

            int indentationSize = 4;
            Collection<Character> brackets = List.of('{', '[', '(');
            Collection<Character> delimiters = Collections.singleton(',');

            formatter = PrettyFormatter.of(brackets, delimiters, indentationSize);
        }

        @Override
        void startBoard() {
            stringBuilder.append('{');
        }

        @Override
        void endBoard() {
            stringBuilder.append("}\n");
        }

        @Override
        void startType(FieldType fieldType) {
            String typeName = getTypeName(fieldType);

            stringBuilder.append('"')
                    .append(typeName)
                    .append("\": [");
        }

        @Override
        void endType() {
            stringBuilder.append(']');
        }

        @Override
        void separateType() {
            stringBuilder.append(',');
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
            return formatter.format(stringBuilder.toString());
        }

        private static String getTypeName(FieldType fieldType) {
            String fullName = fieldType.toString();
            String shortName = fullName.split("\\.")[1];
            return shortName.toLowerCase(Locale.US) + "s";
        }
    }
}
