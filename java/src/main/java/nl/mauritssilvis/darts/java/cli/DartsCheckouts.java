/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.cli;

import nl.mauritssilvis.darts.java.checkouts.output.CheckoutTableSerializerFactory;
import nl.mauritssilvis.darts.java.output.Serializer;
import nl.mauritssilvis.darts.java.settings.*;
import nl.mauritssilvis.darts.java.tables.CheckoutTable;
import nl.mauritssilvis.darts.java.tables.CheckoutTableGenerator;
import nl.mauritssilvis.darts.java.tables.factory.CheckoutTableGeneratorFactory;
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
public class DartsCheckouts implements Runnable {
    @Spec
    CommandSpec commandSpec;

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
            description = "The checkout table type. Supported values: map.",
            paramLabel = "<table>",
            defaultValue = "map",
            order = 4
    )
    private GeneratorType generatorType;

    @Option(
            names = {"-o", "--output"},
            description = "The output format. Supported values: string.",
            paramLabel = "<output>",
            defaultValue = "string",
            order = 5
    )
    private OutputFormat outputFormat;

    @Parameters(
            description = """
             The minimum score that should be part of the checkout table. If \
             not specified, the minimum is set equal to the maximum score.\
             """,
            arity = "0..1",
            paramLabel = "<minimum score>",
            defaultValue = "-1",
            showDefaultValue = CommandLine.Help.Visibility.NEVER
    )

    private int minScore;

    @Parameters(
            description = "The maximum score that should be part of the checkout table.",
            paramLabel = "<maximum score>",
            showDefaultValue = CommandLine.Help.Visibility.NEVER
    )
    private int maxScore;

    @Override
    public void run() {
    }
}
