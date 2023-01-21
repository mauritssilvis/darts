/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.boards;

import nl.mauritssilvis.darts.checkouts.java.boards.fields.Field;
import nl.mauritssilvis.darts.checkouts.java.boards.fields.Type;
import nl.mauritssilvis.darts.checkouts.java.boards.fields.TypedField;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * An implementation of the {@code Board} interface that represents a standard
 * dartboard.
 * <p>
 * Relevant design patterns: Immutable object.
 */
public class StandardBoard implements Board {
    @Override
    public List<Field> getFields(Type type) {
        return switch (type) {
            case SINGLE -> getSingleFields();
            case DOUBLE -> getDoubleFields();
            case TRIPLE -> getTripleFields();
            case QUADRUPLE -> getQuadrupleFields();
        };
    }

    private static List<Field> getSingleFields() {
        List<Field> fields = IntStream.rangeClosed(1, 20)
                .mapToObj(i -> TypedField.of(Type.SINGLE, i))
                .collect(Collectors.toList());

        fields.add(TypedField.of(Type.SINGLE, 25));

        return Collections.unmodifiableList(fields);
    }

    private static List<Field> getDoubleFields() {
        List<Field> fields = IntStream.rangeClosed(1, 20)
                .mapToObj(i -> TypedField.of(Type.DOUBLE, i))
                .collect(Collectors.toList());

        fields.add(TypedField.of(Type.DOUBLE, 25));

        return Collections.unmodifiableList(fields);
    }

    private static List<Field> getTripleFields() {
        return IntStream.rangeClosed(1, 20)
                .mapToObj(i -> TypedField.of(Type.TRIPLE, i))
                .toList();
    }

    private static List<Field> getQuadrupleFields() {
        return Collections.emptyList();
    }
}
