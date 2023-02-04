/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.checkouts.utils;

import nl.mauritssilvis.darts.java.boards.Field;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class TypedFieldTestUtils {
    private TypedFieldTestUtils() {
    }

    public static List<Field> getFields(String... names) {
        if (names.length == 1) {
            return Collections.singletonList(nl.mauritssilvis.darts.java.boards.common.TypedFieldTestUtils.getField(names[0]));
        }

        return Arrays.stream(names)
                .map(nl.mauritssilvis.darts.java.boards.common.TypedFieldTestUtils::getField)
                .toList();
    }

    public static List<List<Field>> getFieldsPerThrow(Collection<? extends Collection<String>> namesPerThrow) {
        return namesPerThrow.stream()
                .map(names -> names.stream()
                        .map(nl.mauritssilvis.darts.java.boards.common.TypedFieldTestUtils::getField).toList()
                )
                .toList();
    }
}
