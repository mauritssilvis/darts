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
    void retrieveStoredSteps() {
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
    void obtainUnlinkedSteps() {
        List<Integer> steps = List.of(2, 5, 6);
        Path path = new SimplePath(steps);

        List<Boolean> links = List.of(false, false, false);

        Assertions.assertEquals(links, path.getLinks());
    }

    @Test
    void obtainASinglePath() {
        List<Integer> steps = List.of(-1, 10, 5);
        Path path = new SimplePath(steps);

        Assertions.assertEquals(1, path.getMultiplicity());
    }
}
