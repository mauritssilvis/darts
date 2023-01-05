/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.checkouts.java.paths;

import java.util.List;

public interface Path {
    List<Integer> getSteps();

    int getLength();

    List<Boolean> getLinks();

    int getMultiplicity();
}
