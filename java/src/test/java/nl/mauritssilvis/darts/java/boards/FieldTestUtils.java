/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.boards;

import java.util.Collection;
import java.util.List;

/**
 * A set of testing utilities for processing collections of {@code Field}
 * objects.
 */
public final class FieldTestUtils {
    private FieldTestUtils() {
    }

    /**
     * Gets the names of the specified fields.
     *
     * @param fields a collection of fields
     * @return a list of the field names
     */
    public static List<String> getAllNames(Collection<? extends Field> fields) {
        return fields.stream()
                .map(Field::getName)
                .toList();
    }
}
