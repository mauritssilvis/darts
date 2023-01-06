/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.nodes;

import java.util.List;

public interface Node {
    /**
     * Returns the labels of the edges incident to the current node.
     *
     * @return a list of unique integers labeling the edges of the node
     */
    List<Integer> getEdges();
}
