/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.api.finders.checkouts;

import java.util.List;

/**
 * A checkout that represents zero or more sequences of throws of a certain
 * total score.
 * <p>
 * Simple checkouts represent a single sequence of simple throws. In such
 * checkouts, the different throws contain a single field and are not grouped.
 * Interchanging the throws of a simple checkout leads to a sequence of throws
 * that is not represented by that same checkout. In contrast, checkouts with
 * grouped throws represent multiple sequences, one for each permutation of the
 * grouped elements.
 * <p>
 * Relevant design patterns: immutable interface.
 *
 * @author Maurits Silvis
 * @since 0.5.0
 */
public interface Checkout {
    /**
     * Gets the score of this checkout, that is, the total score of the throws
     * of this checkout.
     *
     * @return the score of this checkout
     */
    int getScore();

    /**
     * Gets the throws of this checkout.
     *
     * @return a list of the throws of this checkout
     */
    List<Throw> getThrows();

    /**
     * Gets the multiplicity of this checkout.
     * <p>
     * Simple checkouts, which represent a single sequence of simple throws,
     * have a unit multiplicity. Checkouts with grouped throws represent
     * multiple sequences and, correspondingly, have a multiplicity larger than
     * one.
     *
     * @return the multiplicity of this checkout
     */
    long getMultiplicity();
}
