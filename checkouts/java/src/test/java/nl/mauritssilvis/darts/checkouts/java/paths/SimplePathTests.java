/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

class SimplePathTests {
    @Test
    void getThePathSizeWithCollectionInput() {
        Collection<Integer> steps = List.of(4, 2, 0);
        Path path = SimplePath.of(steps);

        Assertions.assertEquals(steps.size(), path.getSize());
    }

    @Test
    void getThePathSizeWithArrayInput() {
        int[] steps = {6, 0};
        Path path = SimplePath.of(steps);

        Assertions.assertEquals(steps.length, path.getSize());
    }

    @Test
    void getThePathSizeWithVarArgsInput() {
        Path path = SimplePath.of(1, 7);

        Assertions.assertEquals(2, path.getSize());
    }

    @Test
    void getThePathSizeWithEmptyCollectionInput() {
        Collection<Integer> steps = Collections.emptyList();
        Path path = SimplePath.of(steps);

        Assertions.assertEquals(0, path.getSize());
    }

    @Test
    void getThePathSizeWithEmptyArrayInput() {
        int[] steps = {};
        Path path = SimplePath.of(steps);

        Assertions.assertEquals(0, path.getSize());
    }

    @Test
    void getThePathSizeWithEmptyVarArgsInput() {
        Path path = SimplePath.of();

        Assertions.assertEquals(0, path.getSize());
    }

    @Test
    void getThePathLengthWithCollectionInput() {
        Collection<Integer> steps = List.of(5, 3, 1);
        Path path = SimplePath.of(steps);

        Assertions.assertEquals(9, path.getLength());
    }

    @Test
    void getThePathLengthWithArrayInput() {
        int[] steps = {-1, 2, 4, 3};
        Path path = SimplePath.of(steps);

        Assertions.assertEquals(8, path.getLength());
    }

    @Test
    void getThePathLengthWithVarArgsInput() {
        Path path = SimplePath.of(3);

        Assertions.assertEquals(3, path.getLength());
    }

    @Test
    void getThePathLengthWithEmptyCollectionInput() {
        Collection<Integer> steps = Collections.emptyList();
        Path path = SimplePath.of(steps);

        Assertions.assertEquals(0, path.getLength());
    }

    @Test
    void getThePathLengthWithEmptyArrayInput() {
        int[] steps = {};
        Path path = SimplePath.of(steps);

        Assertions.assertEquals(0, path.getLength());
    }

    @Test
    void getThePathLengthWithEmptyVarArgsInput() {
        Path path = SimplePath.of();

        Assertions.assertEquals(0, path.getLength());
    }

    @Test
    void getStoredStepsWithCollectionInput() {
        Collection<Integer> steps = List.of(1, 2, 3);
        Path path = SimplePath.of(steps);

        Assertions.assertEquals(steps, path.getSteps());
    }

    @Test
    void getStoredStepsWithArrayInput() {
        int[] steps = {-1, 3};
        Path path = SimplePath.of(steps);

        Collection<Integer> stepList = Arrays.stream(steps)
                .boxed()
                .toList();

        Assertions.assertEquals(stepList, path.getSteps());
    }

    @Test
    void getStoredStepsWithVarArgsInput() {
        Path path = SimplePath.of(1, 2, 4, 5);

        Collection<Integer> steps = List.of(1, 2, 4, 5);

        Assertions.assertEquals(steps, path.getSteps());
    }

    @Test
    void getStoredStepsWithEmptyCollectionInput() {
        Collection<Integer> steps = Collections.emptyList();
        Path path = SimplePath.of(steps);

        Assertions.assertEquals(steps, path.getSteps());
    }

    @Test
    void getStoredStepsWithEmptyArrayInput() {
        int[] steps = {};
        Path path = SimplePath.of(steps);

        Collection<Integer> stepList = Collections.emptyList();

        Assertions.assertEquals(stepList, path.getSteps());
    }

    @Test
    void getStoredStepsWithEmptyVarArgsInput() {
        Path path = SimplePath.of();

        Collection<Integer> steps = Collections.emptyList();

        Assertions.assertEquals(steps, path.getSteps());
    }

    @Test
    void storeIndependentStepsWithCollectionInput() {
        List<Integer> steps = new ArrayList<>(List.of(4, 5, 6));
        Path path = SimplePath.of(steps);

        steps.set(0, 10);

        Assertions.assertNotEquals(steps, path.getSteps());
    }

    @Test
    void storeIndependentStepsWithArrayInput() {
        int[] steps = {-1, 3};
        Path path = SimplePath.of(steps);

        steps[1] = -5;

        List<Integer> stepList = Arrays.stream(steps)
                .boxed()
                .toList();

        Assertions.assertNotEquals(stepList, path.getSteps());
    }

    @Test
    void storeImmutableStepsWithCollectionInput() {
        List<Integer> steps = new ArrayList<>(List.of(4, 5, 6));
        Path path = SimplePath.of(steps);

        List<Integer> storedSteps = path.getSteps();

        Assertions.assertThrows(UnsupportedOperationException.class, () -> storedSteps.set(0, 7));
    }

    @Test
    void storeImmutableStepsWithArrayInput() {
        int[] steps = {0, 1, 2};
        Path path = SimplePath.of(steps);

        List<Integer> storedSteps = path.getSteps();

        Assertions.assertThrows(UnsupportedOperationException.class, () -> storedSteps.set(2, 2));
    }

    @Test
    void storeImmutableStepsWithVarArgsInput() {
        Path path = SimplePath.of(10, 9, 8);

        List<Integer> storedSteps = path.getSteps();

        Assertions.assertThrows(UnsupportedOperationException.class, () -> storedSteps.set(0, 8));
    }

    @Test
    void obtainUngroupedStepsWithCollectionInput() {
        Collection<Integer> steps = List.of(2, 5, 6);
        Path path = SimplePath.of(steps);

        Assertions.assertEquals(steps.size(), path.getGroupCount());
    }

    @Test
    void obtainUngroupedStepsWithArrayInput() {
        int[] steps = {3, 1, 2};
        Path path = SimplePath.of(steps);

        Assertions.assertEquals(steps.length, path.getGroupCount());
    }

    @Test
    void obtainUngroupedStepsWithVarArgsInput() {
        Path path = SimplePath.of(5, 3, 9);

        Assertions.assertEquals(3, path.getGroupCount());
    }

    @Test
    void obtainNoGroupsWithEmptyCollectionInput() {
        Collection<Integer> steps = Collections.emptyList();
        Path path = SimplePath.of(steps);

        Assertions.assertEquals(0, path.getGroupCount());
    }

    @Test
    void obtainNoGroupsWithEmptyArrayInput() {
        int[] steps = {};
        Path path = SimplePath.of(steps);

        Assertions.assertEquals(0, path.getGroupCount());
    }

    @Test
    void obtainNoGroupsWithEmptyVarArgsInput() {
        Path path = SimplePath.of();

        Assertions.assertEquals(0, path.getGroupCount());
    }

    @Test
    void obtainASinglePathWithCollectionInput() {
        Collection<Integer> steps = List.of(-1, 10, 5);
        Path path = SimplePath.of(steps);

        Assertions.assertEquals(1, path.getMultiplicity());
    }

    @Test
    void obtainASinglePathWithArrayInput() {
        int[] steps = {1, 3, 4, 6};
        Path path = SimplePath.of(steps);

        Assertions.assertEquals(1, path.getMultiplicity());
    }

    @Test
    void obtainASinglePathWithVarArgsInput() {
        Path path = SimplePath.of(8, 7, 6, 5, 4);

        Assertions.assertEquals(1, path.getMultiplicity());
    }

    @Test
    void obtainNoPathWithEmptyCollectionInput() {
        Collection<Integer> steps = Collections.emptyList();
        Path path = SimplePath.of(steps);

        Assertions.assertEquals(0, path.getMultiplicity());
    }

    @Test
    void obtainNoPathWithEmptyArrayInput() {
        int[] steps = {};
        Path path = SimplePath.of(steps);

        Assertions.assertEquals(0, path.getMultiplicity());
    }

    @Test
    void obtainNoPathWithEmptyVarArgsInput() {
        Path path = SimplePath.of();

        Assertions.assertEquals(0, path.getMultiplicity());
    }
}
