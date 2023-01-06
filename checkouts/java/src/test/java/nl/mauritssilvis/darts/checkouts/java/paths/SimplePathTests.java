/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class SimplePathTests {
    @Test
    void getThePathLength() {
        List<Integer> steps = List.of(5, 3, 1);
        Path path = new SimplePath(steps);

        Assertions.assertEquals(9, path.getLength());
    }

    @Test
    void getStoredSteps() {
        List<Integer> steps = List.of(1, 2, 3);
        Path path = new SimplePath(steps);

        Assertions.assertEquals(steps, path.getSteps());
    }

    @Test
    void storeAnIndependentCopyOfTheSteps() {
        List<Integer> steps = new ArrayList<>(List.of(4, 5, 6));
        Path path = new SimplePath(steps);

        steps.set(0, 10);

        Assertions.assertNotEquals(steps, path.getSteps());
    }

    @Test
    void storeImmutableSteps() {
        List<Integer> steps = new ArrayList<>(List.of(4, 5, 6));
        Path path = new SimplePath(steps);

        List<Integer> storedSteps = path.getSteps();

        Assertions.assertThrows(UnsupportedOperationException.class, () -> storedSteps.set(0, 7));
    }

    @Test
    void obtainUnlinkedSteps() {
        List<Integer> steps = List.of(2, 5, 6);
        Path path = new SimplePath(steps);

        List<Boolean> links = List.of(false, false, false);

        Assertions.assertEquals(steps.size(), path.getGroupCount());
    }

    @Test
    void obtainASinglePath() {
        List<Integer> steps = List.of(-1, 10, 5);
        Path path = new SimplePath(steps);

        Assertions.assertEquals(1, path.getMultiplicity());
    }
}
