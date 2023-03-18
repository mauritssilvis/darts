/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.core.boards.output;

import nl.mauritssilvis.darts.java.api.boards.Board;
import nl.mauritssilvis.darts.java.api.boards.FieldType;
import nl.mauritssilvis.darts.java.api.output.Serializer;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * An implementation of the generic {@code Serializer} interface that serializes
 * {@code Board} objects to Markdown.
 * <p>
 * Relevant design patterns: strategy, immutable object, static factory method.
 *
 * @author Maurits Silvis
 * @since 0.1.0
 */
final class MarkdownBoardSerializer implements Serializer<Board> {
    private MarkdownBoardSerializer() {
    }

    /**
     * Returns a new {@code MarkdownBoardSerializer}.
     *
     * @return a new {@code MarkdownBoardSerializer}
     */
    public static Serializer<Board> create() {
        return new MarkdownBoardSerializer();
    }

    @Override
    public String serialize(Board object) {
        return new MarkdownBoardPrinter(object).print();
    }

    private static class MarkdownBoardPrinter extends BoardPrinter {
        private final Map<Integer, StringBuilder> builderMap;
        private final String columnFormat;
        private int row;

        MarkdownBoardPrinter(Board board) {
            super(board);

            builderMap = new HashMap<>();
            IntStream.range(0, getNumFields() + 2)
                    .forEach(i -> builderMap.put(i, new StringBuilder()));

            columnFormat = "%1$" + getFieldWidth() + "s";
        }

        @Override
        void startBoard() {
        }

        @Override
        void endBoard() {
            builderMap.values().forEach(e -> e.append("|\n"));
        }

        @Override
        void startType(FieldType fieldType) {
            String typeInitial = getTypeInitial(fieldType);

            builderMap.get(row)
                    .append("| ")
                    .append(columnFormat.formatted(typeInitial))
                    .append(' ');

            nextRow();

            builderMap.get(row)
                    .append("|-")
                    .append(columnFormat.formatted("").replace(' ', '-'))
                    .append(':');

            nextRow();
        }

        @Override
        void endType() {
        }

        @Override
        void separateType() {
        }

        @Override
        void startField() {
            builderMap.get(row).append("| ");
        }

        @Override
        void addField(String name) {
            builderMap.get(row).append(columnFormat.formatted(name));
        }

        @Override
        void endField() {
            builderMap.get(row).append(' ');
            nextRow();
        }

        @Override
        void separateField() {
        }

        @Override
        void startEmptyField() {
            startField();
        }

        @Override
        void addEmptyField() {
            addField("-");
        }

        @Override
        void endEmptyField() {
            endField();
        }

        @Override
        String getString() {
            return builderMap.entrySet().stream()
                    .sorted(Comparator.comparingInt(Map.Entry::getKey))
                    .map(Map.Entry::getValue)
                    .map(StringBuilder::toString)
                    .collect(Collectors.joining());
        }

        private void nextRow() {
            row = (row + 1) % builderMap.size();
        }

        private static String getTypeInitial(FieldType fieldType) {
            String fullName = fieldType.toString();
            String shortName = fullName.split("\\.")[1];
            return shortName.substring(0, 1);
        }
    }
}
