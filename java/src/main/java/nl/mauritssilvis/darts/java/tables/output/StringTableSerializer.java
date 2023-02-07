/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.output;

import nl.mauritssilvis.darts.java.output.Serializer;
import nl.mauritssilvis.darts.java.output.StringSerializer;
import nl.mauritssilvis.darts.java.tables.Table;

/**
 * An implementation of the generic {@code StringSerializer} interface that
 * allows for the creation of objects that can serialize {@code Table} objects
 * using their Java string representation.
 * <p>
 * Relevant design patterns: strategy, immutable object, static factory method.
 */
public final class StringTableSerializer implements StringSerializer<Table> {
    private StringTableSerializer() {
    }

    /**
     * Returns a new {@code StringTableSerializer}.
     *
     * @return a new {@code StringTableSerializer}
     */
    public static Serializer<Table> create() {
        return new StringTableSerializer();
    }
}
