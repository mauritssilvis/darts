/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.boards.common;

import nl.mauritssilvis.darts.java.boards.Field;
import nl.mauritssilvis.darts.java.boards.FieldType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A set of testing utilities that create (collections of) {@code TypedField}
 * objects.
 */
public class TypedFieldTestUtils {
    private TypedFieldTestUtils() {
    }

    /**
     * Returns a field with the specified name.
     *
     * @param name the field name
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
     * @param names the field names
     * @return a list of fields with the specified names
     */
    public static List<Field> getFields(String... names) {
        if (names.length == 1) {
            return Collections.singletonList(getField(names[0]));
        }

        return Arrays.stream(names)
                .map(TypedFieldTestUtils::getField)
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
