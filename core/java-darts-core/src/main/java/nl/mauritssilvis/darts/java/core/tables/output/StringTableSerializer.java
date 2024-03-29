/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.core.tables.output;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import nl.mauritssilvis.darts.java.api.output.Formatter;
import nl.mauritssilvis.darts.java.api.output.Serializer;
import nl.mauritssilvis.darts.java.api.tables.Table;
import nl.mauritssilvis.darts.java.core.output.PrettyFormatter;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * An implementation of the generic {@code Serializer} interface that serializes
 * {@code Table} objects using their Java string representation.
 * <p>
 * Relevant design patterns: strategy, immutable object, simple factory.
 *
 * @author Maurits Silvis
 * @since 0.5.0
 */
@ToString
@EqualsAndHashCode
final class StringTableSerializer implements Serializer<Table> {
    private final Formatter formatter;

    private StringTableSerializer() {
        int indentationSize = 2;
        Collection<Character> brackets = List.of('{', '[', '(');
        Collection<Character> delimiters = Collections.singleton(',');

        formatter = PrettyFormatter.of(brackets, delimiters, indentationSize);
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
