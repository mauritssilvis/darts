/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.output;

/**
 * A serializer.
 * <p>
 * Other than requiring a string as output, this functional interface does not
 * fix the output format of serialization. Implementations can be used to define
 * this property.
 * <p>
 * Relevant design patterns: strategy, immutable interface.
 *
 * @param <T> the type of object to be serialized
 * @author Maurits Silvis
 * @since 0.1.0
 */
@FunctionalInterface
public interface Serializer<T> {
    /**
     * Serializes the specified object and returns the result as a string.
     *
     * @param object the object to serialize
     * @return a string representing the serialized object
     */
    String serialize(T object);
}
