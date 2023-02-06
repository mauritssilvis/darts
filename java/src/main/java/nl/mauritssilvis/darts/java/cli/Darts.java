/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.cli;

import picocli.CommandLine;

@CommandLine.Command(
        name = "darts",
        subcommands = {
                CommandLine.HelpCommand.class,
                DartsBoards.class
        },
        version = {"darts 0.1.0", "Copyright © 2023 Maurits H. Silvis", "SPDX-License-Identifier: GPL-3.0-or-later"},
        mixinStandardHelpOptions = true,
        description = "A computational toolbox aimed at the game of darts",
        showDefaultValues = true
)
public class Darts {
    public static void main(String[] args) {
        new CommandLine(new Darts()).execute(args);
    }
}
