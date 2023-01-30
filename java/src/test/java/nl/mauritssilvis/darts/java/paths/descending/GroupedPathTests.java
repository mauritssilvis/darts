/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.paths.descending;

import nl.mauritssilvis.darts.java.paths.Group;
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
    void storeIndependentSteps() {
        List<Integer> steps = new ArrayList<>(List.of(7, 5, 7));
        Collection<Boolean> grouping = List.of(false, true, true);
        Path path = GroupedPath.of(steps, grouping);

        steps.set(1, 7);

        Assertions.assertNotEquals(steps, path.getSteps());
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
    void countTheGroupsForUngroupedSteps() {
        Collection<Integer> steps = List.of(6, 7, 8, 9);
        Collection<Boolean> grouping = List.of(false, false, false, false);
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(4, path.countGroups());
    }

    @Test
    void countTheGroupsForPartlyGroupedSteps() {
        Collection<Integer> steps = List.of(1, 2, 3);
        Collection<Boolean> grouping = List.of(false, true, false);
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(2, path.countGroups());
    }

    @Test
    void countTheGroupsForGroupedSteps() {
        Collection<Integer> steps = List.of(2, 5, 6);
        Collection<Boolean> grouping = List.of(false, true, true);
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(1, path.countGroups());
    }

    @Test
    void countTheGroupsWithSingletonInput() {
        Collection<Integer> steps = Collections.singletonList(5);
        Collection<Boolean> grouping = Collections.singletonList(false);
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(1, path.countGroups());
    }

    @Test
    void countTheGroupsWithEmptyInput() {
        Collection<Integer> steps = Collections.emptyList();
        Collection<Boolean> grouping = Collections.emptyList();
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(0, path.countGroups());
    }

    @Test
    void countTheGroupsWithAShorterGroupingSignature() {
        Collection<Integer> steps = List.of(5, 6, 7, 8);
        Collection<Boolean> grouping = List.of(true, false);
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(4, path.countGroups());
    }

    @Test
    void countTheGroupsWithALongerGroupingSignature() {
        Collection<Integer> steps = List.of(5, 7);
        Collection<Boolean> grouping = List.of(false, true, true, false, true);
        Path path = GroupedPath.of(steps, grouping);

        Assertions.assertEquals(1, path.countGroups());
    }

    @Test
    void getTheGroupsForUngroupedSteps() {
        Collection<Integer> steps = List.of(3, 2);
        Collection<Boolean> grouping = List.of(false, false);
        Path path = GroupedPath.of(steps, grouping);

        List<Group> groups = path.getGroups();

        Assertions.assertAll(
                () -> Assertions.assertEquals(2, groups.size()),
                () -> Assertions.assertEquals(List.of(3), groups.get(0).getValues()),
                () -> Assertions.assertEquals(List.of(2), groups.get(1).getValues())
        );
    }

    @Test
    void getTheGroupsForPartlyGroupedSteps() {
        Collection<Integer> steps = List.of(4, 3, 2, 2);
        Collection<Boolean> grouping = List.of(false, false, true, true);
        Path path = GroupedPath.of(steps, grouping);

        List<Group> groups = path.getGroups();

        Assertions.assertAll(
                () -> Assertions.assertEquals(2, groups.size()),
                () -> Assertions.assertEquals(List.of(4), groups.get(0).getValues()),
                () -> Assertions.assertEquals(List.of(3, 2, 2), groups.get(1).getValues())
        );
    }

    @Test
    void getTheGroupsForGroupedSteps() {
        Collection<Integer> steps = List.of(7, 8);
        Collection<Boolean> grouping = List.of(false, true);
        Path path = GroupedPath.of(steps, grouping);

        List<Group> groups = path.getGroups();

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, groups.size()),
                () -> Assertions.assertEquals(steps, groups.get(0).getValues())
        );
    }

    @Test
    void getTheGroupsWithSingletonInput() {
        Collection<Integer> steps = Collections.singletonList(7);
        Collection<Boolean> grouping = Collections.singletonList(true);
        Path path = GroupedPath.of(steps, grouping);

        List<Group> groups = path.getGroups();

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, groups.size()),
                () -> Assertions.assertEquals(steps, groups.get(0).getValues())
        );
    }

    @Test
    void getTheGroupsWithEmptyInput() {
        Collection<Integer> steps = Collections.emptyList();
        Collection<Boolean> grouping = Collections.emptyList();
        Path path = GroupedPath.of(steps, grouping);

        List<Group> groups = path.getGroups();

        Assertions.assertEquals(0, groups.size());
    }

    @Test
    void getTheGroupsWithAShorterGroupingSignature() {
        Collection<Integer> steps = List.of(4, 3, 2);
        Collection<Boolean> grouping = List.of(false, true);
        Path path = GroupedPath.of(steps, grouping);

        List<Group> groups = path.getGroups();

        Assertions.assertAll(
                () -> Assertions.assertEquals(2, groups.size()),
                () -> Assertions.assertEquals(List.of(4, 3), groups.get(0).getValues()),
                () -> Assertions.assertEquals(List.of(2), groups.get(1).getValues())
        );
    }

    @Test
    void getTheGroupsWithALongerGroupingSignature() {
        Collection<Integer> steps = List.of(1, 2);
        Collection<Boolean> grouping = List.of(false, false, true, false, true);
        Path path = GroupedPath.of(steps, grouping);

        List<Group> groups = path.getGroups();

        Assertions.assertAll(
                () -> Assertions.assertEquals(2, groups.size()),
                () -> Assertions.assertEquals(List.of(1), groups.get(0).getValues()),
                () -> Assertions.assertEquals(List.of(2), groups.get(1).getValues())
        );
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
                Arguments.of(
                        Collections.emptyList(), Collections.emptyList(), 0
                ),
                Arguments.of(
                        List.of(5), Collections.emptyList(), 1
                ),
                Arguments.of(
                        List.of(3), List.of(false), 1
                ),
                Arguments.of(
                        List.of(2, 4), List.of(false, true), 2
                ),
                Arguments.of(
                        List.of(5, 5, 5), List.of(true, true, true), 1
                ),
                Arguments.of(
                        List.of(3, 7, 7, 3),
                        List.of(true, true, true, true),
                        6
                ),
                Arguments.of(
                        List.of(9, 8, 2, 2, 2),
                        List.of(false, true, true, true, true),
                        20
                ),
                Arguments.of(
                        List.of(2, 2, 1, 2, 2),
                        List.of(true, true, false, true, true),
                        3
                ),
                Arguments.of(
                        List.of(1, 8, 4, 0, 6, 3),
                        List.of(false, true, true, true, true, true),
                        720
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
                )
        );
    }
}
