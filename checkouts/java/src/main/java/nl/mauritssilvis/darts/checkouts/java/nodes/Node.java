/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.nodes;

import java.util.List;


/**
 * A node with outgoing edges having integer weights.
 * <p>
 * For the purposes of the present application, it is not necessary to specify
 * connections to other nodes. Each node can simply be assumed to be connected
 * to one other node. The weights of the edges incident to a node have to be
 * unique, however.
 */
public interface Node {
    /**
     * Gets the weights of the edges incident to this node.
     * <p>
     * For the purposes of the present application, the weights of the edges
     * associated with a node have to be unique.
     *
     * @return a list of unique edge weights
     */
    List<Integer> getWeights();

    /**
     * Determines if this node is disconnected, that is, if this node has no
     * edges.
     *
     * @return a boolean that signals if this node is disconnected
     */
    boolean isDisconnected();
}
