/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.paths.common;

import java.util.List;


/**
 * A node with outgoing edges having integer weights.
 * <p>
 * For the purposes of the present application, it is not necessary to specify
 * connections to other nodes. Each node can simply be assumed to have directed
 * edges to one other node. The weights of the edges incident to a node have to
 * be unique, however.
 * <p>
 * Relevant design pattern: Immutable interface.
 */
public interface Node {
    /**
     * Gets the weights of the outgoing edges of this node.
     * <p>
     * The weights of the outgoing edges of a node have to be unique.
     *
     * @return a list of the unique edge weights of this node
     */
    List<Integer> getWeights();

    /**
     * Determines if this node is connected, that is, if this node has outgoing
     * edges.
     *
     * @return a boolean that signals if this node is connected
     */
    boolean isConnected();
}
