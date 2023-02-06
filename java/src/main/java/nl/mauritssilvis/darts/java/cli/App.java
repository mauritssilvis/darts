/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.cli;

import picocli.CommandLine;

/**
 * A picocli-based command-line application.
 */
public interface App {
    /**
     * Gets the {@code CommandLine} object of this application.
     *
     * @return the {@code CommandLine} object of this application
     */
    CommandLine getCommandLine();

    /**
     * Executes this command-line application with the specified arguments.
     *
     * @param args the command-line arguments
     * @return the exit code
     */
    int execute(String[] args);
}
