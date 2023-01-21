/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.boards;

import nl.mauritssilvis.darts.checkouts.java.boards.fields.Field;
import nl.mauritssilvis.darts.checkouts.java.boards.fields.Type;

import java.util.List;

/**
 * A dartboard.
 * <p>
 * Relevant design patterns: Immutable interface.
 */
public interface Board {
    /**
     * Gets the dartboard fields of the specified type.
     *
     * @param type the field type
     * @return a list of fields of the given type
     */
    List<Field> getFields(Type type);
}
