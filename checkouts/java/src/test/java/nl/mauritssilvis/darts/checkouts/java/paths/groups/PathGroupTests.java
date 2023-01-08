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

    @ParameterizedTest
    @MethodSource("withPermutationData")
    void getThePermutationCount(Collection<Integer> values, int permutationCount) {
        Group group = PathGroup.of(values);

        Assertions.assertEquals(permutationCount, group.countPermutations());
    }

    static Stream<Arguments> withPermutationData() {
        return Stream.of(
                Arguments.arguments(
                        Collections.emptyList(), 0
                ),
                Arguments.arguments(
                        List.of(10), 1
                ),
                Arguments.arguments(
                        List.of(3, 3), 1
                ),
                Arguments.arguments(
                        List.of(2, 3), 2
                ),
                Arguments.arguments(
                        List.of(4, 4, 4), 1
                ),
                Arguments.arguments(
                        List.of(7, 8, 8), 3
                ),
                Arguments.arguments(
                        List.of(3, 6, 2), 6
                )
        );
    }
}
