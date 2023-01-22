/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.checkouts.common;

import nl.mauritssilvis.darts.checkouts.java.boards.Field;
import nl.mauritssilvis.darts.checkouts.java.boards.FieldType;
import nl.mauritssilvis.darts.checkouts.java.boards.standard.TypedField;

import java.util.List;
import java.util.stream.IntStream;

public class TypedFieldTestUtils {
    private TypedFieldTestUtils() {
    }

    public static Field getField(String name) {
        FieldType fieldType = getFieldType(name);
        int baseScore = getBaseScore(name, fieldType);
        return TypedField.of(fieldType, baseScore);
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

    public static List<Field> getFields(List<FieldType> fieldTypes, List<Integer> baseScores) {
        return IntStream.range(0, fieldTypes.size())
                .mapToObj(i -> TypedField.of(fieldTypes.get(i), baseScores.get(i)))
                .toList();
    }
}
