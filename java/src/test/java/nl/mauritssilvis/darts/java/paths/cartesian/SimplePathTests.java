/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.paths.cartesian;

import nl.mauritssilvis.darts.java.paths.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class SimplePathTests {
    @Test
    void storeIndependentSteps() {
        List<Integer> steps = new ArrayList<>(List.of(4, 5, 6));
        Path path = SimplePath.of(steps);

        steps.set(0, 10);

        Assertions.assertNotEquals(steps, path.getSteps());
    }

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
    void getImmutableSteps() {
        Collection<Integer> steps = new ArrayList<>(List.of(4, 5, 6));
        Path path = SimplePath.of(steps);

        List<Integer> storedSteps = path.getSteps();

        Assertions.assertThrows(UnsupportedOperationException.class, () -> storedSteps.set(0, 7));
    }

    @Test
    void getTheGrouping() {
        Collection<Integer> steps = List.of(4, 5, 9);
        Path path = SimplePath.of(steps);

        Assertions.assertEquals(List.of(false, false, false), path.getGrouping());
    }

    @Test
    void getAnImmutableGrouping() {
        Collection<Integer> steps = List.of(1, 9);
        Path path = SimplePath.of(steps);

        List<Boolean> grouping = path.getGrouping();

        Assertions.assertThrows(UnsupportedOperationException.class, grouping::clear);
    }

    @Test
    void getTheGroupingWithSingletonInput() {
        Collection<Integer> steps = Collections.singletonList(6);
        Path path = SimplePath.of(steps);

        Assertions.assertEquals(List.of(false), path.getGrouping());
    }

    @Test
    void getTheGroupingWithEmptyInput() {
        Collection<Integer> steps = Collections.emptyList();
        Path path = SimplePath.of(steps);

        Assertions.assertEquals(Collections.emptyList(), path.getGrouping());
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

    @Test
    void getEqualPaths() {
        Collection<Integer> steps1 = List.of(1, 2, 3, 4, 5);
        Path path1 = SimplePath.of(steps1);

        Collection<Integer> steps2 = List.of(1, 2, 3, 4, 5);
        Path path2 = SimplePath.of(steps2);

        Assertions.assertEquals(path1, path2);
    }

    @Test
    void getUnequalPaths() {
        Collection<Integer> steps1 = List.of(5, 4, 3, 2, 1);
        Path path1 = SimplePath.of(steps1);

        Collection<Integer> steps2 = List.of(1, 2, 3, 4, 5);
        Path path2 = SimplePath.of(steps2);

        Assertions.assertNotEquals(path1, path2);
    }
}
