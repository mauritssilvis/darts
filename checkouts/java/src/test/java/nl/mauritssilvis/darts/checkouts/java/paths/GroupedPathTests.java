/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
}
