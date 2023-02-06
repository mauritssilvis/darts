/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.cli;

import nl.mauritssilvis.darts.java.boards.Board;
import nl.mauritssilvis.darts.java.boards.factory.BoardFactory;
import nl.mauritssilvis.darts.java.settings.BoardType;
import picocli.CommandLine;

@CommandLine.Command(
        name = "boards",
        description = "Print a dartboard.",
        showDefaultValues = true
)
public class DartsBoards implements Runnable {
    @CommandLine.Spec
    CommandLine.Model.CommandSpec commandSpec;

    @CommandLine.Parameters(
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
