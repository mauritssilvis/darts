/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.cli;

import nl.mauritssilvis.darts.java.boards.Board;
import nl.mauritssilvis.darts.java.boards.factory.BoardFactory;
import nl.mauritssilvis.darts.java.boards.output.BoardSerializerFactory;
import nl.mauritssilvis.darts.java.output.Serializer;
import nl.mauritssilvis.darts.java.settings.BoardType;
import nl.mauritssilvis.darts.java.settings.OutputFormat;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

/**
 * The command-line interface for the boards subcommand.
 * <p>
 * This command-line interface was implemented using picocli.
 */
@Command(
        name = "boards",
        description = "Print a dartboard.",
        showDefaultValues = true
)
class DartsBoards implements Runnable {
    @Spec
    private CommandSpec commandSpec;

    @Option(
            names = {"-o", "--output"},
            description = "The output format. Supported values: string.",
            paramLabel = "<output>",
            defaultValue = "string"
    )
    private OutputFormat outputFormat;

    @Parameters(
            description = "Dartboard type(s). Supported values: London, Quadro, Yorkshire.",
            paramLabel = "<board>"
    )
    private BoardType boardType;

    public void run() {
        Board board = BoardFactory.create(boardType);

        Serializer<Board> serializer = BoardSerializerFactory.create(outputFormat);
        String output = serializer.serialize(board);

        commandSpec.commandLine().getOut().println(output);
    }
}
