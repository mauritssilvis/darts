/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.output;

import nl.mauritssilvis.darts.java.output.Serializer;
import nl.mauritssilvis.darts.java.tables.Table;

/**
 * An implementation of the generic {@code Serializer} interface that serializes
 * {@code Table} objects to HTML.
 * <p>
 * Relevant design patterns: strategy, immutable object, static factory method.
 */
public final class HtmlTableSerializer implements Serializer<Table> {
    private HtmlTableSerializer() {
    }

    /**
     * Returns a new {@code HtmlTableSerializer}.
     *
     * @return a new {@code HtmlTableSerializer}
     */
    public static Serializer<Table> create() {
        return new HtmlTableSerializer();
    }

    @Override
    public String serialize(Table object) {
        throw new UnsupportedOperationException(
                "Checkout tables do not yet support serialization to HTML."
        );
    }
}
