/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.paths.descending;

import nl.mauritssilvis.darts.java.paths.Path;
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
    void storeIndependentSteps() {
        List<Integer> steps = new ArrayList<>(List.of(7, 5, 7));
        Collection<Boolean> grouping = List.of(false, true, true);
        Path path = GroupedPath.of(steps, grouping);

        steps.set(1, 7);

        Assertions.assertNotEquals(steps, path.getSteps());
    }

    @Test
    void storeAnIndependentGrouping() {
        List<Integer> steps = List.of(7, 5, 7);
        List<Boolean> grouping = new ArrayList<>(List.of(false, true));
        Path path = GroupedPath.of(steps, grouping);

        long multiplicity = path.getMultiplicity();

        grouping.set(1, false);

        long newMultiplicity = path.getMultiplicity();

        Assertions.assertEquals(multiplicity, newMultiplicity);
    }

    @Test
    void getTheLength() {
        Collection<Integer> steps = List.of(5, 3, 1);
        Collection<Boolean> grouping = List.of(false, true, false);
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(9, path.getLength());
    }

    @Test
    void getTheLengthWithSingletonInput() {
        Collection<Integer> steps = Collections.singletonList(3);
        Collection<Boolean> grouping = Collections.singletonList(false);
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(3, path.getLength());
    }

    @Test
    void getTheLengthWithEmptyInput() {
        Collection<Integer> steps = Collections.emptyList();
        Collection<Boolean> grouping = Collections.emptyList();
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(0, path.getLength());
    }

    @Test
    void getTheLengthWithAShorterGroupingSignature() {
        Collection<Integer> steps = List.of(2, 3, 1, 1);
        Collection<Boolean> grouping = List.of(false, false);
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(7, path.getLength());
    }

    @Test
    void getTheLengthWithALongerGroupingSignature() {
        Collection<Integer> steps = List.of(3, 1);
        Collection<Boolean> grouping = List.of(false, true, false);
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(4, path.getLength());
    }

    @Test
    void getTheSteps() {
        Collection<Integer> steps = List.of(3, 2, 3);
        Collection<Boolean> grouping = List.of(true, true, true);
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(steps, path.getSteps());
    }

    @Test
    void getTheStepsWithSingletonInput() {
        Collection<Integer> steps = Collections.singletonList(7);
        Collection<Boolean> grouping = Collections.singletonList(true);
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(steps, path.getSteps());
    }

    @Test
    void getTheStepsWithEmptyInput() {
        Collection<Integer> steps = Collections.emptyList();
        Collection<Boolean> grouping = Collections.emptyList();
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(steps, path.getSteps());
    }

    @Test
    void getTheStepsWithAShorterGroupingSignature() {
        Collection<Integer> steps = List.of(1, 2);
        Collection<Boolean> grouping = Collections.emptyList();
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(steps, path.getSteps());
    }

    @Test
    void getTheStepsWithALongerGroupingSignature() {
        Collection<Integer> steps = List.of(1);
        Collection<Boolean> grouping = List.of(false, false);
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(steps, path.getSteps());
    }

    @Test
    void getImmutableSteps() {
        Collection<Integer> steps = new ArrayList<>(List.of(3, 2, 3));
        Collection<Boolean> grouping = List.of(false, true);
        Path path = GroupedPath.of(steps, grouping);

        List<Integer> storedSteps = path.getSteps();

        Assertions.assertThrows(UnsupportedOperationException.class, () -> storedSteps.set(0, 4));
    }

    @Test
    void getTheGroupingForUngroupedSteps() {
        Collection<Integer> steps = List.of(3, 2);
        Collection<Boolean> grouping = List.of(false, false);
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(grouping, path.getGrouping());
    }

    @Test
    void getTheGroupingForPartlyGroupedSteps() {
        Collection<Integer> steps = List.of(4, 3, 2, 2);
        Collection<Boolean> grouping = List.of(false, false, true, true);
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(grouping, path.getGrouping());
    }

    @Test
    void getTheGroupingForGroupedSteps() {
        Collection<Integer> steps = List.of(7, 8);
        Collection<Boolean> grouping = List.of(false, true);
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(grouping, path.getGrouping());
    }

    @Test
    void getTheGroupingWithSingletonInput() {
        Collection<Integer> steps = Collections.singletonList(7);
        Collection<Boolean> grouping = Collections.singletonList(false);
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(grouping, path.getGrouping());
    }

    @Test
    void getTheGroupingWithEmptyInput() {
        Collection<Integer> steps = Collections.emptyList();
        Collection<Boolean> grouping = Collections.emptyList();
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(grouping, path.getGrouping());
    }

    @Test
    void getTheGroupingWithAGroupedElement() {
        Collection<Integer> steps = Collections.singletonList(9);
        Collection<Boolean> grouping = Collections.singletonList(true);
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(List.of(false), path.getGrouping());
    }

    @Test
    void getTheGroupingWithAGroupedFirstElement() {
        Collection<Integer> steps = List.of(1, 2);
        Collection<Boolean> grouping = List.of(true, false);
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(List.of(false, false), path.getGrouping());
    }

    @Test
    void getTheGroupingWithAShorterGroupingSignature() {
        Collection<Integer> steps = List.of(4, 3, 2);
        Collection<Boolean> grouping = List.of(false, true);
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(List.of(false, true, false), path.getGrouping());
    }

    @Test
    void getTheGroupingWithALongerGroupingSignature() {
        Collection<Integer> steps = List.of(1, 2);
        Collection<Boolean> grouping = List.of(false, false, true, false, true);
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(List.of(false, false), path.getGrouping());
    }

    @Test
    void getAnImmutableGrouping() {
        Collection<Integer> steps = List.of(4, 2, 1);
        Collection<Boolean> grouping = List.of(false, false, true);
        Path path = GroupedPath.of(steps, grouping);

        List<Boolean> storedGrouping = path.getGrouping();

        Assertions.assertThrows(UnsupportedOperationException.class, storedGrouping::clear);
    }

    @ParameterizedTest
    @MethodSource("withMultiplicityData")
    void getTheMultiplicity(
            Collection<Integer> steps,
            Collection<Boolean> grouping,
            int multiplicity
    ) {
        Path path = GroupedPath.of(steps, grouping);
        Assertions.assertEquals(multiplicity, path.getMultiplicity());
    }

    private static Stream<Arguments> withMultiplicityData() {
        return Stream.of(
                // Ungrouped steps
                Arguments.of(
                        Collections.emptyList(), Collections.emptyList(), 0
                ),
                Arguments.of(
                        Collections.emptyList(), List.of(true), 0
                ),
                Arguments.of(
                        List.of(3), List.of(false), 1
                ),
                Arguments.of(
                        List.of(5), Collections.emptyList(), 1
                ),
                Arguments.of(
                        List.of(7), List.of(false, true), 1
                ),
                Arguments.of(
                        List.of(1, 2), List.of(false, false), 1
                ),
                Arguments.of(
                        List.of(2, 3), List.of(false), 1
                ),
                Arguments.of(
                        List.of(4, 4), List.of(false, false, true), 1
                ),
                Arguments.of(
                        List.of(9, 7, 8), List.of(false, false, false), 1
                ),
                Arguments.of(
                        List.of(3, 3, 2), List.of(false), 1
                ),
                Arguments.of(
                        List.of(5, 7, 6), List.of(false, false, false, false), 1
                ),
                // Partly grouped steps
                Arguments.of(
                        List.of(2, 2, 1, 2, 2),
                        List.of(true, true, false, true, true),
                        3
                ),
                Arguments.of(
                        List.of(1, 2, 6, 7, 7, 2, 7),
                        List.of(false, true, true, false, true, true, true),
                        6 * 4
                ),
                Arguments.of(
                        List.of(1, 3, 5, 7, 9, 6, 6),
                        List.of(false, true, false, true, true, true, true),
                        2 * 60
                ),
                Arguments.of(
                        List.of(7, 8, 7, 8, 7, 8, 7),
                        List.of(false, true, true, true, true, true, false),
                        20
                ),
                Arguments.of(
                        List.of(3, 4, 4, 5, 6, 6, 6, 6),
                        List.of(false, true, true, true, false, true, true, true),
                        12
                ),
                Arguments.of(
                        List.of(1, 6, 6, 5, 5, 2, 3, 3, 1),
                        List.of(true, false, true, true, true, true, false, true, true),
                        30 * 3
                ),
                Arguments.of(
                        List.of(1, 2, 2, 3, 7, 8, 7, 7, 2, 3),
                        List.of(true, true, true, true, false, false, true, true, false, true),
                        12 * 3 * 2),
                Arguments.of(
                        List.of(1, 1, 1, 3, 3, 2, 2, 3, 3, 4, 4),
                        List.of(false, true, true, true, true, false, true, true, true, true, true),
                        10 * 90
                ),
                // Fully grouped steps
                Arguments.of(
                        List.of(2, 4), List.of(false, true), 2
                ),
                Arguments.of(
                        List.of(3, 3), List.of(false, true, false), 1
                ),
                Arguments.of(
                        List.of(3, 6, 2), List.of(false, true, true), 6
                ),
                Arguments.of(
                        List.of(7, 8, 8), List.of(true, true, true, false), 3
                ),
                Arguments.of(
                        List.of(5, 5, 5), List.of(true, true, true), 1
                ),
                Arguments.of(
                        List.of(0, 8, 5, 9),
                        List.of(true, true, true, true),
                        24
                ),
                Arguments.of(
                        List.of(4, 3, 4, 7),
                        List.of(false, true, true, true),
                        12
                ),
                Arguments.of(
                        List.of(3, 7, 7, 3),
                        List.of(true, true, true, true, false),
                        6
                ),
                Arguments.of(
                        List.of(9, 6, 9, 9),
                        List.of(false, true, true, true),
                        4
                ),
                Arguments.of(
                        List.of(8, 8, 8, 8),
                        List.of(true, true, true, true),
                        1
                ),
                Arguments.of(
                        List.of(1, 3, 5, 9, 7),
                        List.of(false, true, true, true, true),
                        120
                ),
                Arguments.of(
                        List.of(4, 1, 2, 3, 4),
                        List.of(false, true, true, true, true),
                        60
                ),
                Arguments.of(
                        List.of(3, 3, 5, 4, 4),
                        List.of(false, true, true, true, true),
                        30
                ),
                Arguments.of(
                        List.of(9, 8, 2, 2, 2),
                        List.of(false, true, true, true, true),
                        20
                ),
                Arguments.of(
                        List.of(1, 2, 1, 2, 1),
                        List.of(false, true, true, true, true),
                        10
                ),
                Arguments.of(
                        List.of(0, 0, 0, 0, 0),
                        List.of(false, true, true, true, true),
                        1
                ),
                Arguments.of(
                        List.of(1, 8, 4, 0, 6, 3),
                        List.of(false, true, true, true, true, true),
                        720
                )
        );
    }

    @Test
    void getEqualPaths() {
        Collection<Integer> steps1 = List.of(5, 7, 8);
        Collection<Boolean> grouping1 = List.of(true, true, true, true);
        Path path1 = GroupedPath.of(steps1, grouping1);

        Collection<Integer> steps2 = List.of(5, 7, 8);
        Collection<Boolean> grouping2 = List.of(false, true, true);
        Path path2 = GroupedPath.of(steps2, grouping2);

        Assertions.assertAll(
                () -> Assertions.assertEquals(path1, path2),
                () -> Assertions.assertEquals(path1.hashCode(), path2.hashCode())
        );
    }

    @Test
    void getUnequalPaths() {
        Collection<Integer> steps1 = List.of(8, 8, 8);
        Collection<Boolean> grouping1 = List.of(false, true, true);
        Path path1 = GroupedPath.of(steps1, grouping1);

        Collection<Integer> steps2 = List.of(5, 6, 7);
        Collection<Boolean> grouping2 = List.of(false, false, false);
        Path path2 = GroupedPath.of(steps2, grouping2);

        Assertions.assertNotEquals(path1, path2);
    }

    @Test
    void getUnequalPathsForDifferentSteps() {
        Collection<Boolean> grouping = List.of(false, true, true);

        Collection<Integer> steps1 = List.of(3, 2, 1);
        Path path1 = GroupedPath.of(steps1, grouping);

        Collection<Integer> steps2 = List.of(1, 2, 3);
        Path path2 = GroupedPath.of(steps2, grouping);

        Assertions.assertNotEquals(path1, path2);
    }

    @Test
    void getUnequalPathsForADifferentGroupingSignature() {
        Collection<Integer> steps = List.of(3, 7, 4, 8);

        Collection<Boolean> grouping1 = List.of(false, true, true);
        Path path1 = GroupedPath.of(steps, grouping1);

        Collection<Boolean> grouping2 = List.of(false, true, true, true);
        Path path2 = GroupedPath.of(steps, grouping2);

        Assertions.assertNotEquals(path1, path2);
    }

    @Test
    void convertToAString() {
        Collection<Integer> steps = List.of(1, 3, 5);
        Collection<Boolean> grouping = List.of(false, false, true);
        Path path = GroupedPath.of(steps, grouping);

        String str = path.toString();

        Assertions.assertAll(
                () -> Assertions.assertTrue(str.contains(path.getClass().getSimpleName())),
                () -> Assertions.assertTrue(str.contains("length")),
                () -> Assertions.assertTrue(str.contains("steps")),
                () -> Assertions.assertTrue(str.contains("grouping")),
                () -> Assertions.assertTrue(str.contains("multiplicity"))
        );
    }
}
