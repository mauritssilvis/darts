/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.nodes;

import java.util.Arrays;
import java.util.Collection;

public class UnsortedNode extends BaseNode {
    private UnsortedNode(Collection<Integer> edges) {
        super(edges);
    }

    public static Node of(Collection<Integer> edges) {
        return new UnsortedNode(edges);
    }

    public static Node of(int... edges) {
        return of(Arrays.stream(edges).boxed().toList());
    }
}
