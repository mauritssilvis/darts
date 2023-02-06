/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.cli;

import nl.mauritssilvis.darts.java.boards.Board;
import nl.mauritssilvis.darts.java.boards.factory.BoardFactory;
import nl.mauritssilvis.darts.java.settings.BoardType;
import nl.mauritssilvis.darts.java.settings.OutputType;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(
        name = "boards",
        description = "Print a dartboard.",
        showDefaultValues = true
)
public class DartsBoards implements Runnable {
    @Spec
    CommandLine.Model.CommandSpec commandSpec;

    @Option(
            names = {"-f", "--format"},
            description = {"The output format. Supported values: string."},
            paramLabel = "<output format>",
            defaultValue = "string"
    )
    private OutputType outputType;

    @Parameters(
            description = "Dartboard type(s). Supported values: London, Quadro, Yorkshire.",
            arity = "1..*",
            paramLabel = "<board type>"
    )
    private BoardType boardType;

    public void run() {
        Board board = BoardFactory.create(boardType);
        commandSpec.commandLine().getOut().println(board);
    }
}
