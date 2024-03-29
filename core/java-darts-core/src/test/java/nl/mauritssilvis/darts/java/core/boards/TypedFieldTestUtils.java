/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.core.boards;

import nl.mauritssilvis.darts.java.api.boards.Field;
import nl.mauritssilvis.darts.java.api.boards.FieldType;

import java.util.Collection;
import java.util.List;

/**
 * A set of testing utilities that create (collections of) {@code TypedField}
 * objects.
 *
 * @author Maurits Silvis
 * @since 0.5.0
 */
public final class TypedFieldTestUtils {
    private TypedFieldTestUtils() {
    }

    /**
     * Returns a field with the specified name.
     *
     * @param name the name
     * @return a field with the specified name
     */
    public static Field getField(String name) {
        FieldType fieldType = getFieldType(name);
        int baseScore = getBaseScore(name, fieldType);

        return TypedField.of(fieldType, baseScore);
    }

    /**
     * Returns a list of fields with the specified names.
     *
     * @param names a collection of names
     * @return a list of fields with the specified names
     */
    public static List<Field> getFields(Collection<String> names) {
        return names.stream()
                .map(TypedFieldTestUtils::getField)
                .toList();
    }

    /**
     * Returns a list of fields per throw with the specified names.
     *
     * @param namesPerThrow a collection names per throw
     * @return a list of fields per throw with the specified names
     */
    public static List<List<Field>> getFieldsPerThrow(Collection<? extends Collection<String>> namesPerThrow) {
        return namesPerThrow.stream()
                .map(TypedFieldTestUtils::getFields)
                .toList();
    }

    private static FieldType getFieldType(CharSequence name) {
        char first = name.charAt(0);

        return switch (first) {
            case 'D' -> FieldType.DOUBLE;
            case 'T' -> FieldType.TRIPLE;
            case 'Q' -> FieldType.QUADRUPLE;
            default -> FieldType.SINGLE;
        };
    }

    private static int getBaseScore(String name, FieldType fieldType) {
        int index = fieldType == FieldType.SINGLE ? 0 : 1;
        return Integer.parseInt(name.substring(index));
    }
}
