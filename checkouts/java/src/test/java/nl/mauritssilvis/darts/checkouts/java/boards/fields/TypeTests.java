/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.boards.fields;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TypeTests {
    @Test
    void getTheShorthandOfTheSingleType() {
        Type type = Type.SINGLE;
        String shorthand = "";

        Assertions.assertEquals(shorthand, type.getShorthand());
    }

    @Test
    void getTheShorthandOfTheDoubleType() {
        Type type = Type.DOUBLE;
        String shorthand = "D";

        Assertions.assertEquals(shorthand, type.getShorthand());
    }

    @Test
    void getTheShorthandOfTheTripleType() {
        Type type = Type.TRIPLE;
        String shorthand = "T";

        Assertions.assertEquals(shorthand, type.getShorthand());
    }

    @Test
    void getTheShorthandOfTheQuadrupleType() {
        Type type = Type.QUADRUPLE;
        String shorthand = "Q";

        Assertions.assertEquals(shorthand, type.getShorthand());
    }

    @Test
    void getTheMultiplierOfTheSingleType() {
        Type type = Type.SINGLE;
        int multiplier = 1;

        Assertions.assertEquals(multiplier, type.getMultiplier());
    }

    @Test
    void getTheMultiplierOfTheDoubleType() {
        Type type = Type.DOUBLE;
        int multiplier = 2;

        Assertions.assertEquals(multiplier, type.getMultiplier());
    }

    @Test
    void getTheMultiplierOfTheTripleType() {
        Type type = Type.TRIPLE;
        int multiplier = 3;

        Assertions.assertEquals(multiplier, type.getMultiplier());
    }

    @Test
    void getTheMultiplierOfTheQuadrupleType() {
        Type type = Type.QUADRUPLE;
        int multiplier = 4;

        Assertions.assertEquals(multiplier, type.getMultiplier());
    }
}
