/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.checkouts;

import nl.mauritssilvis.darts.java.boards.Field;
import nl.mauritssilvis.darts.java.boards.FieldTestUtils;

import java.util.Collection;
import java.util.List;

/**
 * A set of testing utilities for processing collections of {@code Throw}
 * objects.
 */
public final class ThrowTestUtils {
    private ThrowTestUtils() {
    }

    /**
     * Gets the fields of the specified throws.
     *
     * @param throwCollection a collection of throws
     * @return a list of the fields per throw
     */
    public static List<List<Field>> getAllFields(Collection<? extends Throw> throwCollection) {
        return throwCollection.stream()
                .map(Throw::getFields)
                .toList();
    }

    /**
     * Gets the names of the fields of the specified throws.
     *
     * @param throwCollection a collection of throws
     * @return a list of the field names per throw
     */
    public static List<List<String>> getAllNames(Collection<? extends Throw> throwCollection) {
        return getAllFields(throwCollection).stream()
                .map(FieldTestUtils::getAllNames)
                .toList();
    }
}
