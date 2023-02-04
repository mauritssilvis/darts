/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.checkouts.utils;

import nl.mauritssilvis.darts.java.boards.Field;

import java.util.Collection;
import java.util.List;

public final class TypedFieldTestUtils {
    private TypedFieldTestUtils() {
    }

    public static List<List<Field>> getFieldsPerThrow(Collection<? extends Collection<String>> namesPerThrow) {
        return namesPerThrow.stream()
                .map(names -> names.stream()
                        .map(nl.mauritssilvis.darts.java.boards.common.TypedFieldTestUtils::getField).toList()
                )
                .toList();
    }
}
