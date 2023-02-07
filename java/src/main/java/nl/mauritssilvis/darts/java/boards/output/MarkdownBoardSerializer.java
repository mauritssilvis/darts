/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.boards.output;

import nl.mauritssilvis.darts.java.boards.Board;
import nl.mauritssilvis.darts.java.boards.Field;
import nl.mauritssilvis.darts.java.boards.FieldType;
import nl.mauritssilvis.darts.java.output.Serializer;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * An implementation of the generic {@code Serializer} interface that serializes
 * {@code Board} objects to Markdown.
 * <p>
 * Relevant design patterns: strategy, immutable object, static factory method.
 */
public final class MarkdownBoardSerializer implements Serializer<Board> {
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
        List<List<Field>> allFields = getAllFields(object);
        int columnWidth = getColumnWidth(allFields);

        StringBuilder stringBuilder = new StringBuilder();

        writeHeader(stringBuilder, allFields, columnWidth);
        writeFields(stringBuilder, allFields, columnWidth);

        return stringBuilder.toString();
    }

    private static int getColumnWidth(Collection<? extends Collection<? extends Field>> selectedFields) {
        return selectedFields.stream()
                .flatMap(Collection::stream)
                .map(Field::getName)
                .mapToInt(String::length)
                .max()
                .orElse(0);
    }

    private static List<List<Field>> getAllFields(Board object) {
        return Arrays.stream(FieldType.values())
                .map(object::getFields)
                .filter(Predicate.not(Collection::isEmpty))
                .toList();
    }

    private static void writeHeader(
            StringBuilder stringBuilder,
            Collection<? extends List<? extends Field>> allFields,
            int columnWidth
    ) {
        String padding = IntStream.range(0, columnWidth)
                .mapToObj(i -> " ")
                .collect(Collectors.joining());

        String rule = padding.replace(" ", "-");

        List<String> columnNames = allFields.stream()
                .map(fields -> fields.get(0))
                .map(Field::getFieldType)
                .map(MarkdownBoardSerializer::getFieldInitial)
                .toList();

        IntStream.range(0, allFields.size())
                .mapToObj(i -> String.format("| %1$" + columnWidth + "s ", columnNames.get(i)))
                .forEach(stringBuilder::append);

        stringBuilder.append("|\n");

        IntStream.range(0, allFields.size())
                .mapToObj(i -> "|-" + rule + ":")
                .forEach(stringBuilder::append);

        stringBuilder.append("|\n");
    }

    private static String getFieldInitial(FieldType fieldType) {
        String fullName = fieldType.toString();
        String shortName = fullName.split("\\.")[1];
        return shortName.substring(0, 1);
    }

    private static void writeFields(
            StringBuilder stringBuilder,
            Collection<? extends Collection<? extends Field>> allFields,
            int columnWidth
    ) {
        List<List<String>> allNames = allFields.stream()
                .map(fields -> fields.stream()
                        .map(Field::getName)
                        .toList())
                .toList();

        int numRows = allNames.stream()
                .mapToInt(Collection::size)
                .max()
                .orElse(0);

        String empty = IntStream.range(0, columnWidth + 2)
                .mapToObj(i -> " ")
                .collect(Collectors.joining());

        for (int i = 0; i < numRows; i++) {
            for (List<String> names : allNames) {
                if (names.size() <= i) {
                    stringBuilder.append("|").append(empty);
                } else {
                    String name = names.get(i);
                    String paddedName = String.format("%1$" + columnWidth + "s", name);
                    stringBuilder.append("| ").append(paddedName).append(' ');
                }
            }

            stringBuilder.append("|\n");
        }
    }
}
