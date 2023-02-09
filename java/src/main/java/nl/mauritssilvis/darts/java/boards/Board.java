/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.boards;

import java.util.List;

/**
 * A dartboard.
 * <p>
 * Relevant design patterns: immutable interface.
 */
@FunctionalInterface
public interface Board {
    /**
     * Gets the fields of this dartboard of the specified type.
     *
     * @param fieldType the field type
     * @return a list of fields of the specified type
     */
    List<Field> getFields(FieldType fieldType);
}
