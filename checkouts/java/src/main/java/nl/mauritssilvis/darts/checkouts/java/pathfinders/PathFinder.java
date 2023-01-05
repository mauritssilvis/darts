/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.pathfinders;

import nl.mauritssilvis.darts.checkouts.java.paths.Path;

import java.util.List;
import java.util.Set;

public interface PathFinder {
    List<Path> find(List<Set<Integer>> steps, int target);
}
