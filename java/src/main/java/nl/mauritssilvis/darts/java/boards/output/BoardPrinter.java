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
 * representations of dartboards.
 * <p>
 * Relevant design patterns: template method.
 */
abstract class BoardPrinter {
    private final List<FieldType> fieldTypes;
    private final Map<FieldType, List<Field>> fieldsMap;
    private final int numFields;
    private final int fieldWidth;

    /**
     * Creates a new {@code BoardPrinter}.
     *
     * @param board a dartboard
     */
    BoardPrinter(Board board) {
        fieldTypes = Arrays.stream(FieldType.values())
                .filter(fieldType -> !board.getFields(fieldType).isEmpty())
                .toList();

        fieldsMap = fieldTypes.stream()
                .collect(Collectors.toMap(Function.identity(), board::getFields, (e1, e2) -> e1, LinkedHashMap::new));

        numFields = determineMaxNumFields(fieldsMap.values());
        fieldWidth = determineFieldWidth(fieldsMap.values());
    }

    /**
     * Gets the maximum number of fields per field type.
     * <p>
     * This property can be used for formatting purposes.
     *
     * @return the maximum number of fields per field type
     */
    int getNumFields() {
        return numFields;
    }

    /**
     * Gets the field character width.
     * <p>
     * This property can be used for formatting purposes.
     *
     * @return the field character width
     */
    int getFieldWidth() {
        return fieldWidth;
    }

    /**
     * Returns a string representation of the stored dartboard.
     * <p>
     * This method directly and indirectly relies on abstract template methods
     * that have to be implemented by child classes.
     *
     * @return a string representation of the stored dartboard
     */
    String print() {
        startBoard();
        processTypes();
        endBoard();

        return getString();
    }

    /**
     * Starts the string representation of the stored dartboard.
     */
    abstract void startBoard();

    /**
     * Ends the string representation of the stored dartboard.
     */
    abstract void endBoard();

    /**
     * Starts the string representation of a field type.
     *
     * @param fieldType the field type
     */
    abstract void startType(FieldType fieldType);

    /**
     * Ends the string representation of a field type.
     */
    abstract void endType();

    /**
     * Separates the string representations of different field types.
     */
    abstract void separateType();

    /**
     * Starts the string representation of a field.
     */
    abstract void startField();

    /**
     * Adds the string representation of a field.
     *
     * @param name the field name
     */
    abstract void addField(String name);

    /**
     * Ends the string representation of a field.
     */
    abstract void endField();

    /**
     * Separates the string representations of different fields.
     */
    abstract void separateField();

    /**
     * Starts the string representation of a missing field.
     */
    abstract void startEmptyField();

    /**
     * Adds the string representation of a missing field.
     */
    abstract void addEmptyField();

    /**
     * Ends the string representation of a missing field.
     */
    abstract void endEmptyField();

    /**
     * Gets the current string representation of the stored dartboard.
     *
     * @return the current string representation of the stored dartboard
     */
    abstract String getString();

    private static int determineMaxNumFields(Collection<? extends Collection<? extends Field>> fieldsPerType) {
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
}
