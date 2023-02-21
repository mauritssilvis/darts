/*
 * Copyright © 2023 Maurits Silvis
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
 * This command-line interface was implemented using picocli.
 * <p>
 * Relevant design patterns: static factory method, lazy initialization.
 *
 * @author Maurits Silvis
 * @since 0.1.0
 */
@Command(
        name = "darts",
        subcommands = {
                CommandLine.HelpCommand.class,
                BoardsCommand.class,
                CheckoutsCommand.class
        },
        versionProvider = Version.class,
        mixinStandardHelpOptions = true,
        header = {"darts -- A computational toolbox aimed at the game of darts", ""},
        // description = "%nDescription.",
        optionListHeading = "%nOptions:%n",
        showDefaultValues = true,
        commandListHeading = "%nCommands:%n",
        footerHeading = "%n",
        footer = {"Copyright © 2023 Maurits Silvis", "SPDX-License-Identifier: GPL-3.0-or-later"}
)
public final class DartsApp implements App {
    private CommandLine commandLine;

    private DartsApp() {
    }

    /**
     * Executes the darts application with the specified command-line
     * arguments.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        create().execute(args);
    }

    /**
     * Returns a new {@code Darts} command-line application.
     *
     * @return a new {@code Darts} command-line application
     */
    public static App create() {
        return new DartsApp();
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
