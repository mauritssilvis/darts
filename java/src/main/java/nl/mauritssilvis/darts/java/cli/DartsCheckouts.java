/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.cli;

import nl.mauritssilvis.darts.java.output.Serializer;
import nl.mauritssilvis.darts.java.settings.*;
import nl.mauritssilvis.darts.java.tables.Table;
import nl.mauritssilvis.darts.java.tables.TableGenerator;
import nl.mauritssilvis.darts.java.tables.factory.TableGeneratorFactory;
import nl.mauritssilvis.darts.java.tables.output.TableSerializerFactory;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

/**
 * The command-line interface for the checkouts subcommand.
 * <p>
 * This command-line interface was implemented using picocli.
 */
@Command(
        name = "checkouts",
        description = "Generate a darts checkout table.",
        sortSynopsis = false,
        showDefaultValues = true
)
class DartsCheckouts implements Runnable {
    @Spec
    private CommandSpec commandSpec;

    @Option(
            names = {"-b", "--board"},
            description = "The dartboard type. Supported values: London, Quadro, Yorkshire.",
            paramLabel = "<board>",
            defaultValue = "London",
            order = 0
    )
    private BoardType boardType;

    @Option(
            names = {"-i", "--check-in"},
            description = "The check-in type. Supported values: any, master, double.",
            paramLabel = "<check-in>",
            defaultValue = "any",
            order = 1
    )
    private CheckType checkInType;

    @Option(
            names = {"-j", "--checkout"},
            description = "The checkout type. Supported values: any, master, double.",
            paramLabel = "<checkout>",
            defaultValue = "double",
            order = 2
    )
    private CheckType checkoutType;

    @Option(
            names = {"-f", "--finder"},
            description = "The checkout finder type. Supported values: Cartesian, descending.",
            paramLabel = "<finder>",
            defaultValue = "descending",
            order = 3
    )
    private FinderType finderType;

    @Option(
            names = {"-t", "--table"},
            description = "The checkout table type. Supported values: ascending.",
            paramLabel = "<table>",
            defaultValue = "ascending",
            order = 4
    )
    private TableType tableType;

    @Option(
            names = {"-o", "--output"},
            description = "The output format. Supported values: string.",
            paramLabel = "<output>",
            defaultValue = "string",
            order = 5
    )
    private OutputFormat outputFormat;

    @Parameters(
            description = "The minimum score that should be part of the checkout table.",
            paramLabel = "<minimum>",
            showDefaultValue = CommandLine.Help.Visibility.NEVER
    )
    private int minScore;

    @Parameters(
            description = "The maximum score that should be part of the checkout table.",
            paramLabel = "<maximum>",
            showDefaultValue = CommandLine.Help.Visibility.NEVER
    )
    private int maxScore;

    @Override
    public void run() {
        TableGenerator tableGenerator = TableGeneratorFactory.create(
                tableType, boardType, checkInType, checkoutType, finderType
        );

        Table table = tableGenerator.generate(minScore, maxScore);

        Serializer<Table> serializer = TableSerializerFactory.create(outputFormat);
        String output = serializer.serialize(table);

        commandSpec.commandLine().getOut().println(output);
    }
}
