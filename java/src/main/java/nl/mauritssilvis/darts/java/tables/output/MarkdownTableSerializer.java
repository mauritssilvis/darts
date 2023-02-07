/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.output;

import nl.mauritssilvis.darts.java.output.Serializer;
import nl.mauritssilvis.darts.java.tables.Table;

/**
 * An implementation of the generic {@code Serializer} interface that serializes
 * {@code Table} objects to Markdown.
 * <p>
 * Relevant design patterns: strategy, immutable object, static factory method.
 */
public final class MarkdownTableSerializer implements Serializer<Table> {
    private MarkdownTableSerializer() {
    }

    /**
     * Returns a new {@code MarkdownTableSerializer}.
     *
     * @return a new {@code MarkdownTableSerializer}
     */
    public static Serializer<Table> create() {
        return new MarkdownTableSerializer();
    }

    @Override
    public String serialize(Table object) {
        return null;
    }
}
