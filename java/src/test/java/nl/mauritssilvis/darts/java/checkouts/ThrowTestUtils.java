/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.checkouts;

import nl.mauritssilvis.darts.java.boards.Field;

import java.util.Collection;
import java.util.List;

/**
 * A set of testing utilities for processing collections of {@code Throw}
 * objects.
 */
public class ThrowTestUtils {
    /**
     * Gets the fields of the specified throws.
     *
     * @param throwList a collection of throws
     * @return a list of the lists of fields of the specified throws
     */
    public static List<List<Field>> getAllFields(Collection<? extends Throw> throwList) {
        return throwList.stream()
                .map(Throw::getFields)
                .toList();
    }
}
