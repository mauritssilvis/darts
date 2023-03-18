/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.finders.checkouts;

import nl.mauritssilvis.darts.java.api.finders.checkouts.Throw;
import nl.mauritssilvis.darts.java.boards.TypedFieldTestUtils;

import java.util.Collection;
import java.util.List;

/**
 * A set of testing utilities that create (collections of) {@code CompoundThrow}
 * objects.
 *
 * @author Maurits Silvis
 * @since 0.1.0
 */
final class CompoundThrowTestUtils {
    private CompoundThrowTestUtils() {
    }

    /**
     * Returns a list of throws with the specified names.
     *
     * @param namesPerThrow a collection of names per throw
     * @return a list of throws with the specified names
     */
    static List<Throw> getThrows(Collection<? extends Collection<String>> namesPerThrow) {
        return namesPerThrow.stream()
                .map(TypedFieldTestUtils::getFields)
                .map(CompoundThrow::of)
                .toList();
    }
}
