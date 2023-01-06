/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.nodes;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class BasicNode implements Node {
    private final List<Integer> weights;

    private BasicNode(Stream<Integer> weights) {
        this.weights = weights
                .distinct()
                .toList();
    }

    public static Node with(Collection<Integer> weights) {
        return new BasicNode(weights.stream());
    }

    public static Node with(int... weights) {
        return new BasicNode(Arrays.stream(weights).boxed());
    }

    @Override
    public List<Integer> getWeights() {
        return weights;
    }

    @Override
    public boolean isDisconnected() {
        return weights.isEmpty();
    }
}
