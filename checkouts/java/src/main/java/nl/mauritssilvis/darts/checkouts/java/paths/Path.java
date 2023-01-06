/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.paths;

import java.util.List;

public interface Path {
    int getLength();

    List<Integer> getSteps();

    int getSize();

    List<Boolean> getLinks();

    int getMultiplicity();
}
