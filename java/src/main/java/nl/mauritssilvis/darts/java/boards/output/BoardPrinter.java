/*
 * Copyright © 2023 Maurits Silvis
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

        numFields = determineNumFields(fieldsMap.values());
        fieldWidth = determineFieldWidth(fieldsMap.values());
    }

    int getNumFields() {
        return numFields;
    }

    int getFieldWidth() {
        return fieldWidth;
    }

    String print() {
        startBoard();
        processTypes();
        endBoard();

        return getString();
    }

    private void processTypes() {
        for (int i = 0; i < fieldTypes.size(); i++) {
            FieldType fieldType = fieldTypes.get(i);
            processType(fieldType);

            if (i != fieldTypes.size() - 1) {
                separateType();
            }
        }
    }

    private void processType(FieldType fieldType) {
        startType(fieldType);
        processFields(fieldsMap.get(fieldType));
        endType();
    }

    private void processFields(List<? extends Field> fields) {
        for (int i = 0; i < fields.size(); i++) {
            Field field = fields.get(i);
            processField(field);

            if (i != fields.size() - 1) {
                separateField();
            }
        }

        for (int i = fields.size(); i < numFields; i++) {
            processEmptyField();
        }
    }

    private void processField(Field field) {
        startField();
        addField(field.getName());
        endField();
    }

    private void processEmptyField() {
        startEmptyField();
        addEmptyField();
        endEmptyField();
    }

    abstract void startBoard();

    abstract void endBoard();

    abstract void startType(FieldType fieldType);

    abstract void endType();

    abstract void separateType();

    abstract void startField();

    abstract void addField(String name);

    abstract void endField();

    abstract void separateField();

    abstract void startEmptyField();

    abstract void addEmptyField();

    abstract void endEmptyField();

    abstract String getString();

    private static int determineNumFields(Collection<? extends Collection<? extends Field>> fieldsPerType) {
        return fieldsPerType.stream()
                .mapToInt(Collection::size)
                .max()
                .orElse(0);
    }

    private static int determineFieldWidth(Collection<? extends Collection<? extends Field>> fieldsPerType) {
        return fieldsPerType.stream()
                .flatMap(Collection::stream)
                .map(Field::getName)
                .mapToInt(String::length)
                .max()
                .orElse(0);
    }
}
