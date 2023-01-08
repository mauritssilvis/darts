/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.paths.groups;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

class BasicGroupTests {
    @Test
    void getTheValues() {
        Collection<Integer> values = List.of(1, 2, 7);
        Group group = BasicGroup.of(values);

        Assertions.assertEquals(values, group.getValues());
    }

    @Test
    void getTheValuesWithEmptyInput() {
        Collection<Integer> values = Collections.emptyList();
        Group group = BasicGroup.of(values);

        Assertions.assertEquals(values, group.getValues());
    }

    @Test
    void storeIndependentValues() {
        List<Integer> values = new ArrayList<>(List.of(7, 5, 7));
        Group group = BasicGroup.of(values);

        values.set(0, 8);

        Assertions.assertNotEquals(values, group.getValues());
    }

    @Test
    void storeImmutableValues() {
        List<Integer> values = new ArrayList<>(List.of(3, 2, 3));
        Group group = BasicGroup.of(values);

        List<Integer> storedValues = group.getValues();

        Assertions.assertThrows(UnsupportedOperationException.class, () -> storedValues.set(1, 3));
    }

    @ParameterizedTest
    @MethodSource("withPermutationData")
    void getThePermutationCount(Collection<Integer> values, int permutations) {
        Group group = BasicGroup.of(values);

        Assertions.assertEquals(permutations, group.countPermutations());
    }

    private static Stream<Arguments> withPermutationData() {
        return Stream.of(
                Arguments.of(Collections.emptyList(), 0),
                Arguments.of(List.of(10), 1),
                Arguments.of(List.of(3, 3), 1),
                Arguments.of(List.of(2, 3), 2),
                Arguments.of(List.of(4, 4, 4), 1),
                Arguments.of(List.of(7, 8, 8), 3),
                Arguments.of(List.of(3, 6, 2), 6),
                Arguments.of(List.of(8, 8, 8, 8), 1),
                Arguments.of(List.of(9, 6, 9, 9), 4),
                Arguments.of(List.of(3, 6, 6, 3), 6),
                Arguments.of(List.of(4, 3, 4, 7), 12),
                Arguments.of(List.of(0, 8, 5, 9), 24),
                Arguments.of(List.of(0, 0, 0, 0, 0), 1),
                Arguments.of(List.of(1, 2, 1, 2, 1), 10),
                Arguments.of(List.of(7, 8, 7, 9, 7), 20),
                Arguments.of(List.of(3, 3, 5, 4, 4), 30),
                Arguments.of(List.of(4, 1, 2, 3, 4), 60),
                Arguments.of(List.of(1, 3, 5, 9, 7), 120)
        );
    }
}
