/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.paths;

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

class GroupedPathTests {
    @Test
    void getThePathSize() {
        Collection<Integer> steps = List.of(1, 2, 3);
        Collection<Boolean> grouping = List.of(false, false, false);
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(steps.size(), path.getSize());
    }

    @Test
    void getThePathSizeWithEmptyInput() {
        Collection<Integer> steps = Collections.emptyList();
        Collection<Boolean> grouping = Collections.emptyList();
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(0, path.getSize());
    }

    @Test
    void getThePathSizeWithAShorterGroupingSignature() {
        Collection<Integer> steps = List.of(3, 7, 8, 1);
        Collection<Boolean> grouping = List.of(true, false);
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(steps.size(), path.getSize());
    }

    @Test
    void getThePathSizeWithALongerGroupingSignature() {
        Collection<Integer> steps = List.of(5);
        Collection<Boolean> grouping = List.of(false, true, false);
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(steps.size(), path.getSize());
    }

    @Test
    void getThePathLength() {
        Collection<Integer> steps = List.of(5, 3, 1);
        Collection<Boolean> grouping = List.of(false, true, false);
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(9, path.getLength());
    }

    @Test
    void getThePathLengthWithEmptyInput() {
        Collection<Integer> steps = Collections.emptyList();
        Collection<Boolean> grouping = Collections.emptyList();
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(0, path.getLength());
    }

    @Test
    void getThePathLengthWithAShorterGroupingSignature() {
        Collection<Integer> steps = List.of(2, 3, 1, 1);
        Collection<Boolean> grouping = List.of(false, false);
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(7, path.getLength());
    }

    @Test
    void getThePathLengthWithALongerGroupingSignature() {
        Collection<Integer> steps = List.of(3, 1);
        Collection<Boolean> grouping = List.of(false, true, false);
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(4, path.getLength());
    }

    @Test
    void getStoredSteps() {
        Collection<Integer> steps = List.of(3, 2, 3);
        Collection<Boolean> grouping = List.of(true, true, true);
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(steps, path.getSteps());
    }

    @Test
    void getStoredStepsWithEmptyInput() {
        Collection<Integer> steps = Collections.emptyList();
        Collection<Boolean> grouping = Collections.emptyList();
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(steps, path.getSteps());
    }

    @Test
    void getStoredStepsWithAShorterGroupingSignature() {
        Collection<Integer> steps = List.of(1, 2);
        Collection<Boolean> grouping = Collections.emptyList();
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(steps, path.getSteps());
    }

    @Test
    void getStoredStepsWithALongerGroupingSignature() {
        Collection<Integer> steps = List.of(1);
        Collection<Boolean> grouping = List.of(false, false);
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(steps, path.getSteps());
    }

    @Test
    void storeIndependentSteps() {
        List<Integer> steps = new ArrayList<>(List.of(7, 5, 7));
        Collection<Boolean> grouping = List.of(false, true, true);
        Path path = GroupedPath.of(steps, grouping);

        steps.set(1, 7);

        Assertions.assertNotEquals(steps, path.getSteps());
    }

    @Test
    void storeImmutableSteps() {
        List<Integer> steps = new ArrayList<>(List.of(3, 2, 3));
        Collection<Boolean> grouping = List.of(false, true);
        Path path = GroupedPath.of(steps, grouping);

        List<Integer> storedSteps = path.getSteps();

        Assertions.assertThrows(UnsupportedOperationException.class, () -> storedSteps.set(0, 4));
    }

    @Test
    void getTheGroupCountForPartlyGroupedSteps() {
        Collection<Integer> steps = List.of(1, 2, 3);
        Collection<Boolean> grouping = List.of(false, true, false);
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(2, path.getGroupCount());
    }

    @Test
    void getTheGroupCountForGroupedSteps() {
        Collection<Integer> steps = List.of(2, 5, 6);
        Collection<Boolean> grouping = List.of(false, true, true);
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(1, path.getGroupCount());
    }

    @Test
    void getTheGroupCountWithEmptyInput() {
        Collection<Integer> steps = Collections.emptyList();
        Collection<Boolean> grouping = Collections.emptyList();
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(0, path.getGroupCount());
    }

    @Test
    void getTheGroupCountWithAShorterGroupingSignature() {
        Collection<Integer> steps = List.of(5, 6, 7, 8);
        Collection<Boolean> grouping = List.of(true, false);
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(4, path.getGroupCount());
    }

    @Test
    void getTheGroupCountWithALongerGroupingSignature() {
        Collection<Integer> steps = List.of(5, 7);
        Collection<Boolean> grouping = List.of(false, true, true, false, true);
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(1, path.getGroupCount());
    }

    @ParameterizedTest
    @MethodSource("withMultiplicityData")
    void getThePathMultiplicity(
            Collection<Integer> steps,
            Collection<Boolean> grouping,
            int multiplicity) {

        Path path = GroupedPath.of(steps, grouping);
        Assertions.assertEquals(multiplicity, path.getMultiplicity());
    }

    static Stream<Arguments> withMultiplicityData() {
        return Stream.of(
                Arguments.arguments(
                        Collections.emptyList(), Collections.emptyList(), 0
                ),
                Arguments.arguments(
                        List.of(3), List.of(false), 1
                ),
                Arguments.arguments(
                        List.of(2, 4), List.of(false, true), 2
                ),
                Arguments.arguments(
                        List.of(5, 5, 5), List.of(true, true, true), 1
                ),
                Arguments.arguments(
                        List.of(3, 7, 7, 3),
                        List.of(true, true, true, true),
                        6
                ),
                Arguments.arguments(
                        List.of(9, 8, 2, 2, 2),
                        List.of(false, true, true, true, true),
                        20
                ),
                Arguments.arguments(
                        List.of(2, 2, 1, 2, 2),
                        List.of(true, true, false, true, true),
                        3
                ),
                Arguments.arguments(
                        List.of(1, 8, 4, 0, 6, 3),
                        List.of(false, true, true, true, true, true),
                        720
                ),
                Arguments.arguments(
                        List.of(1, 2, 6, 7, 7, 2, 7),
                        List.of(false, true, true, false, true, true, true),
                        6 * 4
                ),
                Arguments.arguments(
                        List.of(1, 3, 5, 7, 9, 6, 6),
                        List.of(false, true, false, true, true, true, true),
                        2 * 60
                ),
                Arguments.arguments(
                        List.of(7, 8, 7, 8, 7, 8, 7),
                        List.of(false, true, true, true, true, true, false),
                        20
                ),
                Arguments.arguments(
                        List.of(3, 4, 4, 5, 6, 6, 6, 6),
                        List.of(false, true, true, true, false, true, true, true),
                        12
                ),
                Arguments.arguments(
                        List.of(1, 6, 6, 5, 5, 2, 3, 3, 1),
                        List.of(true, false, true, true, true, true, false, true, true),
                        30 * 3
                ),
                Arguments.arguments(
                        List.of(1, 2, 2, 3, 7, 8, 7, 7, 2, 3),
                        List.of(true, true, true, true, false, false, true, true, false, true),
                        12 * 3 * 2),
                Arguments.arguments(
                        List.of(1, 1, 1, 3, 3, 2, 2, 3, 3, 4, 4),
                        List.of(false, true, true, true, true, false, true, true, true, true, true),
                        10 * 90
                )
        );
    }
}
