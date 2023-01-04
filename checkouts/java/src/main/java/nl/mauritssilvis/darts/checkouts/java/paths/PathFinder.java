/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.paths;

import java.util.List;
import java.util.Set;

public interface PathFinder {
    Set<List<Integer>> find(List<Set<Integer>> steps, int target);
}
