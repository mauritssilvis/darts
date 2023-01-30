/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.paths.cartesian;

import nl.mauritssilvis.darts.java.paths.Group;
import nl.mauritssilvis.darts.java.paths.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class SimplePathTests {
    @Test
    void getTheLength() {
        Collection<Integer> steps = List.of(5, 3, 1);
        Path path = SimplePath.of(steps);

        Assertions.assertEquals(9, path.getLength());
    }

    @Test
    void getTheLengthWithSingletonInput() {
        Collection<Integer> steps = Collections.singletonList(6);
        Path path = SimplePath.of(steps);

        Assertions.assertEquals(6, path.getLength());
    }

    @Test
    void getTheLengthWithEmptyInput() {
        Collection<Integer> steps = Collections.emptyList();
        Path path = SimplePath.of(steps);

        Assertions.assertEquals(0, path.getLength());
    }

    @Test
    void getTheSteps() {
        Collection<Integer> steps = List.of(1, 2, 3);
        Path path = SimplePath.of(steps);

        Assertions.assertEquals(steps, path.getSteps());
    }

    @Test
    void getTheStepsWithSingletonInput() {
        Collection<Integer> steps = Collections.singletonList(3);
        Path path = SimplePath.of(steps);

        Assertions.assertEquals(steps, path.getSteps());
    }

    @Test
    void getTheStepsWithEmptyInput() {
        Collection<Integer> steps = Collections.emptyList();
        Path path = SimplePath.of(steps);

        Assertions.assertEquals(steps, path.getSteps());
    }

    @Test
    void storeIndependentSteps() {
        List<Integer> steps = new ArrayList<>(List.of(4, 5, 6));
        Path path = SimplePath.of(steps);

        steps.set(0, 10);

        Assertions.assertNotEquals(steps, path.getSteps());
    }

    @Test
    void getImmutableSteps() {
        Collection<Integer> steps = new ArrayList<>(List.of(4, 5, 6));
        Path path = SimplePath.of(steps);

        List<Integer> storedSteps = path.getSteps();

        Assertions.assertThrows(UnsupportedOperationException.class, () -> storedSteps.set(0, 7));
    }

    @Test
    void countTheGroups() {
        Collection<Integer> steps = List.of(2, 5, 6);
        Path path = SimplePath.of(steps);

        Assertions.assertEquals(steps.size(), path.countGroups());
    }

    @Test
    void countTheGroupsWithSingletonInput() {
        Collection<Integer> steps = Collections.singletonList(9);
        Path path = SimplePath.of(steps);

        Assertions.assertEquals(1, path.countGroups());
    }

    @Test
    void countTheGroupsWithEmptyInput() {
        Collection<Integer> steps = Collections.emptyList();
        Path path = SimplePath.of(steps);

        Assertions.assertEquals(0, path.countGroups());
    }

    @Test
    void getTheGroups() {
        Collection<Integer> steps = List.of(3, 6);
        Path path = SimplePath.of(steps);

        List<Group> groups = path.getGroups();

        Assertions.assertAll(
                () -> Assertions.assertEquals(steps.size(), groups.size()),
                () -> Assertions.assertEquals(List.of(3), groups.get(0).getValues()),
                () -> Assertions.assertEquals(List.of(6), groups.get(1).getValues())
        );
    }

    @Test
    void getTheGroupsWithSingletonInput() {
        Collection<Integer> steps = Collections.singletonList(2);
        Path path = SimplePath.of(steps);

        List<Group> groups = path.getGroups();

        Assertions.assertEquals(1, groups.size());
    }

    @Test
    void getTheGroupsWithEmptyInput() {
        Collection<Integer> steps = Collections.emptyList();
        Path path = SimplePath.of(steps);

        List<Group> groups = path.getGroups();

        Assertions.assertEquals(0, groups.size());
    }

    @Test
    void getTheMultiplicity() {
        Collection<Integer> steps = List.of(-1, 10, 5);
        Path path = SimplePath.of(steps);

        Assertions.assertEquals(1, path.getMultiplicity());
    }

    @Test
    void getTheMultiplicityWithSingletonInput() {
        Collection<Integer> steps = Collections.singletonList(7);
        Path path = SimplePath.of(steps);

        Assertions.assertEquals(1, path.getMultiplicity());
    }

    @Test
    void getTheMultiplicityWithEmptyInput() {
        Collection<Integer> steps = Collections.emptyList();
        Path path = SimplePath.of(steps);

        Assertions.assertEquals(0, path.getMultiplicity());
    }
}
