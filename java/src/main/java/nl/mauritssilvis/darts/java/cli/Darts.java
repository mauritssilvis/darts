/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.cli;

import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.PrintWriter;

/**
 * The command-line interface for darts -- A computational toolbox aimed at the
 * game of darts.
 * <p>
 * Relevant design patterns: static factory method, lazy initialization.
 */
@Command(
        name = "darts",
        subcommands = {
                CommandLine.HelpCommand.class,
                DartsBoards.class
        },
        version = {"darts 0.1.0", "Copyright © 2023 Maurits H. Silvis", "SPDX-License-Identifier: GPL-3.0-or-later"},
        mixinStandardHelpOptions = true,
        description = "A computational toolbox aimed at the game of darts",
        showDefaultValues = true,
        footer = {"Copyright © 2023 Maurits H. Silvis", "SPDX-License-Identifier: GPL-3.0-or-later"}
)
public final class Darts implements App {
    private CommandLine commandLine;

    private Darts() {
    }

    /**
     * Executes the darts application with the specified command-line
     * arguments.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        App darts = create();
        darts.execute(args);
    }

    /**
     * Returns a new {@code Darts} command-line application.
     *
     * @return a new {@code Darts} command-line application.
     */
    public static App create() {
        return new Darts();
    }

    @Override
    public App setOut(PrintWriter out) {
        getCommandLine().setOut(out);
        return this;
    }

    @Override
    public App setErr(PrintWriter err) {
        getCommandLine().setErr(err);
        return this;
    }

    @Override
    public int execute(String[] args) {
        return getCommandLine().execute(args);
    }

    private CommandLine getCommandLine() {
        if (commandLine == null) {
            commandLine = new CommandLine(this)
                    .setCaseInsensitiveEnumValuesAllowed(true);
        }

        return commandLine;
    }
}
