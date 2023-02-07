/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.output;

import nl.mauritssilvis.darts.java.boards.Field;
import nl.mauritssilvis.darts.java.checkouts.Checkout;
import nl.mauritssilvis.darts.java.checkouts.Throw;
import nl.mauritssilvis.darts.java.output.Serializer;
import nl.mauritssilvis.darts.java.tables.Table;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * An implementation of the generic {@code Serializer} interface that serializes
 * {@code Table} objects to JSON.
 * <p>
 * Relevant design patterns: strategy, immutable object, static factory method.
 */
public final class JsonTableSerializer implements Serializer<Table> {
    private JsonTableSerializer() {
    }

    /**
     * Returns a new {@code JsonTableSerializer}.
     *
     * @return a new {@code JsonTableSerializer}
     */
    public static Serializer<Table> create() {
        return new JsonTableSerializer();
    }

    @Override
    public String serialize(Table object) {
        throw new UnsupportedOperationException(
                "Checkout tables do not yet support serialization to JSON."
        );
    }
}
