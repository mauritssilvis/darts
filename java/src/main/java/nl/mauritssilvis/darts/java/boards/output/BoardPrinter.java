/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.boards.output;

import nl.mauritssilvis.darts.java.boards.Board;
import nl.mauritssilvis.darts.java.boards.Field;
import nl.mauritssilvis.darts.java.boards.FieldType;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * An abstract class that defines an algorithm for getting string
 * representations of boards.
 * <p>
 * Relevant design patterns: template method.
 */
abstract class BoardPrinter {
    private final List<FieldType> fieldTypes;
    private final Map<FieldType, List<Field>> fieldsMap;
    private final int numFields;
    private final int fieldWidth;

    BoardPrinter(Board board) {
        fieldTypes = Arrays.stream(FieldType.values())
                .filter(fieldType -> !board.getFields(fieldType).isEmpty())
                .toList();

        fieldsMap = fieldTypes.stream()
                .collect(Collectors.toMap(Function.identity(), board::getFields, (e1, e2) -> e1, LinkedHashMap::new));

        numFields = fieldsMap.values().stream()
                .mapToInt(Collection::size)
                .max()
                .orElse(0);

        fieldWidth = fieldsMap.values().stream()
                .flatMap(Collection::stream)
                .map(Field::getName)
                .mapToInt(String::length)
                .max()
                .orElse(0);
    }

    int getNumFields() {
        return numFields;
    }

    int getFieldWidth() {
        return fieldWidth;
    }

    String print() {
        startTable();

        for (int i = 0; i < fieldTypes.size(); i++) {
            FieldType fieldType = fieldTypes.get(i);

            startFields(fieldType);
            addFields(fieldType);
            endFields();

            if (i != fieldTypes.size() - 1) {
                separateFields();
            } else {
                endLastFields();
            }
        }

        endTable();

        return getString();
    }

    abstract void startTable();

    abstract void endTable();

    abstract void startFields(FieldType fieldType);

    void addFields(FieldType fieldType) {
        List<Field> fields = fieldsMap.get(fieldType);

        for (int i = 0; i < fields.size(); i++) {
            Field field = fields.get(i);

            startField();
            addField(field.getName());
            endField();

            if (i != fields.size() - 1) {
                separateField();
            } else {
                endLastField();
            }
        }

        separateEmptyFields();

        for (int i = fields.size(); i < numFields; i++) {
            startEmptyField();
            addEmptyField();
            endEmptyField();

            if (i != numFields - 1) {
                separateField();
                separateEmptyField();
            }

        }
    }

    abstract void endFields();

    abstract void separateFields();

    abstract void endLastFields();

    abstract void separateEmptyFields();

    abstract void startField();

    abstract void addField(String name);

    abstract void endField();

    abstract void separateField();

    abstract void endLastField();

    abstract void startEmptyField();

    abstract void addEmptyField();

    abstract void endEmptyField();

    abstract void separateEmptyField();

    abstract void endLastEmptyField();

    abstract String getString();
}
