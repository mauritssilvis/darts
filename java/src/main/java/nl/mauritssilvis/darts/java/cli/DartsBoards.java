/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.cli;

import picocli.CommandLine;

@CommandLine.Command(
        name = "boards",
        description = "Print a supported board"
)
public class DartsBoards implements Runnable {
    public void run() {
        System.out.println("boards");
    }
}
