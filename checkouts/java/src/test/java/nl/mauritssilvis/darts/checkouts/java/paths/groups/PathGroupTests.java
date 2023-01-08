/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.paths.groups;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class PathGroupTests {
    @Test
    void getStoredValues() {
        Collection<Integer> values = List.of(1, 2, 7);
        Group group = PathGroup.of(values);

        Assertions.assertEquals(values, group.getValues());
    }

    @Test
    void getStoredValuesWithEmptyInput() {
        Collection<Integer> values = Collections.emptyList();
        Group group = PathGroup.of(values);

        Assertions.assertEquals(values, group.getValues());
    }

    @Test
    void storeIndependentValues() {
        List<Integer> values = new ArrayList<>(List.of(7, 5, 7));
        Group group = PathGroup.of(values);

        values.set(0, 8);

        Assertions.assertNotEquals(values, group.getValues());
    }

    @Test
    void storeImmutableValues() {
        List<Integer> values = new ArrayList<>(List.of(3, 2, 3));
        Group group = PathGroup.of(values);

        List<Integer> storedValues = group.getValues();

        Assertions.assertThrows(UnsupportedOperationException.class, () -> storedValues.set(1, 3));
    }
}