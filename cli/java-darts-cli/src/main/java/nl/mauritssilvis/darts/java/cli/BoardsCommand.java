/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.cli;

import lombok.ToString;
import nl.mauritssilvis.darts.java.api.boards.Board;
import nl.mauritssilvis.darts.java.api.output.Serializer;
import nl.mauritssilvis.darts.java.api.settings.BoardType;
import nl.mauritssilvis.darts.java.api.settings.OutputFormat;
import nl.mauritssilvis.darts.java.core.boards.BoardFactory;
import nl.mauritssilvis.darts.java.core.boards.output.BoardSerializerFactory;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

/**
 * The command-line interface for the {@code boards} subcommand. This command can be
 * used to print dartboards to the console in various output formats.
 * <p>
 * The {@code boards} command was implemented using picocli.
 *
 * @author Maurits Silvis
 * @since 0.1.0
 */
@Command(
        name = "boards",
        versionProvider = Version.class,
        mixinStandardHelpOptions = true,
        header = {"Print a dartboard.", ""},
        descriptionHeading = "%n",
        description = {
                "Print one of the supported dartboards.",
                "",
                "  darts boards london",
                "  darts boards -o json quadro"
        },
        parameterListHeading = "%nParameters:%n",
        optionListHeading = "%nOptions:%n",
        showDefaultValues = true,
        footerHeading = "%n",
        footer = {
                "Online documentation:",
                "  https://mauritssilvis.nl/software/darts/cli/java-darts-cli",
                "",
                "Copyright © 2023 Maurits Silvis",
                "SPDX-License-Identifier: GPL-3.0-or-later"
        }
)
@ToString
class BoardsCommand implements Runnable {
    @Spec
    private CommandSpec commandSpec;

    @Option(
            names = {"-o", "--output"},
            description = "The output format. Supported values: Markdown, JSON, HTML, string.",
            paramLabel = "<output>",
            defaultValue = "Markdown"
    )
    private OutputFormat outputFormat;

    @Parameters(
            description = "The dartboard type. Supported values: London, Quadro, Yorkshire.",
            paramLabel = "<board>"
    )
    private BoardType boardType;

    /**
     * Creates a new {@code BoardsCommand} object.
     */
    BoardsCommand() {
    }

    public void run() {
        Board board = BoardFactory.create(boardType);

        Serializer<Board> serializer = BoardSerializerFactory.create(outputFormat);
        String output = serializer.serialize(board);

        commandSpec.commandLine().getOut().println(output.strip());
    }
}
