/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.boards.fields;

/**
 * The type of dartboard field.
 */
public enum Type {
    /**
     * A field with a single point value.
     */
    SINGLE("", 1),

    /**
     * A field with a double point value.
     */
    DOUBLE("D", 2),

    /**
     * A field with a triple point value.
     */
    TRIPLE("T", 3),

    /**
     * A field with a quadruple point value.
     */
    QUADRUPLE("Q", 4);

    private final String shorthand;
    private final int multiplier;

    Type(String shorthand, int multiplier) {
        this.shorthand = shorthand;
        this.multiplier = multiplier;
    }

    public String getShorthand() {
        return shorthand;
    }

    public int getMultiplier() {
        return multiplier;
    }
}
