/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.paths.cartesian;

import nl.mauritssilvis.darts.checkouts.java.paths.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class SimpleGroupTests {
    @Test
    void getTheValue() {
        int value = 6;
        Group group = SimpleGroup.of(value);

        Assertions.assertEquals(List.of(value), group.getValues());
    }

    @Test
    void countThePermutations() {
        int value = 3;
        Group group = SimpleGroup.of(value);

        Assertions.assertEquals(1, group.countPermutations());
    }
}
