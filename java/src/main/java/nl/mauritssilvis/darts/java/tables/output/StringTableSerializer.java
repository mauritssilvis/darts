/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.output;

import nl.mauritssilvis.darts.java.output.Formatter;
import nl.mauritssilvis.darts.java.output.Serializer;
import nl.mauritssilvis.darts.java.output.pretty.PrettyFormatter;
import nl.mauritssilvis.darts.java.tables.Table;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * An implementation of the generic {@code Serializer} interface that serializes
 * {@code Table} objects using their Java string representation.
 * <p>
 * Relevant design patterns: strategy, immutable object, static factory method.
 */
public final class StringTableSerializer implements Serializer<Table> {
    private static final int indentationSize = 2;
    private final Collection<Character> brackets = List.of('{', '[', '(');
    private final Collection<Character> delimiters = Collections.singleton(',');
    private final Formatter formatter = PrettyFormatter.of(brackets, delimiters, indentationSize);

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

    @Override
    public String serialize(Table object) {
        return formatter.format(object.toString());
    }
}
