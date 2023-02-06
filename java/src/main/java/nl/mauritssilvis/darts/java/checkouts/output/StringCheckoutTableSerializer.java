/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.checkouts.output;

import nl.mauritssilvis.darts.java.output.Serializer;
import nl.mauritssilvis.darts.java.output.StringSerializer;
import nl.mauritssilvis.darts.java.tables.CheckoutTable;

/**
 * An implementation of the generic {@code Serializer} interface that serializes
 * {@code CheckoutTable} objects using their Java string representation.
 * <p>
 * Relevant design patterns: strategy, immutable object, static factory method.
 */
public final class StringCheckoutTableSerializer extends StringSerializer<CheckoutTable> {
    private StringCheckoutTableSerializer() {
    }

    /**
     * Returns a new {@code StringCheckoutTableSerializer}.
     *
     * @return a new {@code StringCheckoutTableSerializer}
     */
    public static Serializer<CheckoutTable> create() {
        return new StringCheckoutTableSerializer();
    }
}
