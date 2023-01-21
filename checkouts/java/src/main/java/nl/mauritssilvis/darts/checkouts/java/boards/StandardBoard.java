/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.boards;

import nl.mauritssilvis.darts.checkouts.java.boards.fields.Field;
import nl.mauritssilvis.darts.checkouts.java.boards.fields.Type;

import java.util.List;

/**
 * An implementation of the {@code Board} interface that represents a standard
 * dartboard.
 * <p>
 * Relevant design patterns: Immutable object.
 */
public class StandardBoard implements Board {
    @Override
    public List<Field> getFields(Type type) {
        return null;
    }
}
