/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.cli;

import nl.mauritssilvis.darts.java.boards.Board;
import nl.mauritssilvis.darts.java.boards.output.BoardSerializerFactory;
import nl.mauritssilvis.darts.java.boards.types.BoardFactory;
import nl.mauritssilvis.darts.java.output.Serializer;
import nl.mauritssilvis.darts.java.settings.BoardType;
import nl.mauritssilvis.darts.java.settings.OutputFormat;
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
 */
@Command(
        name = "boards",
        mixinStandardHelpOptions = true,
        description = "Print a dartboard.",
        showDefaultValues = true
)
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

    public void run() {
        Board board = BoardFactory.create(boardType);

        Serializer<Board> serializer = BoardSerializerFactory.create(outputFormat);
        String output = serializer.serialize(board);

        commandSpec.commandLine().getOut().println(output.strip());
    }
}
