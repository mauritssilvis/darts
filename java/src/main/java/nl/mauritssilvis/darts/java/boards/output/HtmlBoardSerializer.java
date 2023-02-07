/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.boards.output;

import nl.mauritssilvis.darts.java.boards.Board;
import nl.mauritssilvis.darts.java.boards.FieldType;
import nl.mauritssilvis.darts.java.output.Serializer;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * An implementation of the generic {@code Serializer} interface that serializes
 * {@code Board} objects to HTML.
 * <p>
 * Relevant design patterns: strategy, immutable object, static factory method.
 */
public final class HtmlBoardSerializer implements Serializer<Board> {
    private HtmlBoardSerializer() {
    }

    /**
     * Returns a new {@code HtmlBoardSerializer}.
     *
     * @return a new {@code HtmlBoardSerializer}
     */
    public static Serializer<Board> create() {
        return new HtmlBoardSerializer();
    }

    @Override
    public String serialize(Board object) {
        return new HtmlBoardPrinter(object).print();
    }

    private static class HtmlBoardPrinter extends BoardPrinter {
        private final Map<Integer, StringBuilder> builderMap;
        private final String columnFormat;
        private int row;

        HtmlBoardPrinter(Board board) {
            super(board);

            builderMap = new HashMap<>();
            IntStream.range(0, getNumFields() + 1)
                    .forEach(i -> builderMap.put(i, new StringBuilder()));

            columnFormat = "%1$" + getFieldWidth() + "s";
        }

        @Override
        void startTable() {
            builderMap.values().forEach(e -> e.append("  <tr>"));
        }

        @Override
        void endTable() {
            builderMap.values().forEach(e -> e.append("</tr>\n"));
        }

        @Override
        void startFields(FieldType fieldType) {
            String typeInitial = getTypeInitial(fieldType);

            builderMap.get(row)
                    .append("<th>")
                    .append(String.format(columnFormat, typeInitial))
                    .append("</th>");

            nextRow();
        }

        @Override
        void endFields() {
        }

        @Override
        void separateFields() {
        }

        @Override
        void endLastFields() {
        }

        @Override
        void separateEmptyFields() {
        }

        @Override
        void startField() {
            builderMap.get(row).append("<td>");
        }

        @Override
        void addField(String name) {
            builderMap.get(row).append(String.format(columnFormat, name));
        }

        @Override
        void endField() {
            builderMap.get(row).append("</td>");
            nextRow();
        }

        @Override
        void separateField() {
        }

        @Override
        void endLastField() {
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
        void separateEmptyField() {
        }

        @Override
        void endLastEmptyField() {
        }

        @Override
        String getString() {
            return "<table>\n"
                    + builderMap.entrySet().stream()
                    .sorted(Comparator.comparingInt(Map.Entry::getKey))
                    .map(Map.Entry::getValue)
                    .map(StringBuilder::toString)
                    .collect(Collectors.joining())
                    + "</table>\n";
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
