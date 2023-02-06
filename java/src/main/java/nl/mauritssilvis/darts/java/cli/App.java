/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.cli;

import java.io.PrintWriter;

/**
 * A command-line application.
 * <p>
 * Relevant design patterns: fluent interface.
 */
public interface App {
    /**
     * Sets the writer to use when printing objects.
     * <p>
     * If no writer is set, objects are written to the standard output stream.
     *
     * @param out the print writer
     * @return this application
     */
    App setOut(PrintWriter out);

    /**
     * Sets the writer to use when printing error messages.
     * <p>
     * If no writer is set, messages are written to the standard error stream.
     *
     * @param err the print writer
     * @return this application
     */
    App setErr(PrintWriter err);

    /**
     * Executes this command-line application with the specified arguments.
     *
     * @param args the command-line arguments
     * @return the exit code
     */
    int execute(String[] args);
}
