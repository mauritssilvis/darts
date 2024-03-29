/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.cli;

import lombok.ToString;
import nl.mauritssilvis.darts.java.api.output.Serializer;
import nl.mauritssilvis.darts.java.api.settings.*;
import nl.mauritssilvis.darts.java.api.tables.Table;
import nl.mauritssilvis.darts.java.api.tables.TableGenerator;
import nl.mauritssilvis.darts.java.core.settings.TableSettingsBuilder;
import nl.mauritssilvis.darts.java.core.tables.TableGeneratorFactory;
import nl.mauritssilvis.darts.java.core.tables.output.TableSerializerFactory;
import picocli.CommandLine;
import picocli.CommandLine.*;
import picocli.CommandLine.Model.CommandSpec;

/**
 * The command-line interface for the {@code checkouts} subcommand. This command
 * can be used to generate darts checkout tables and print them to the console
 * in various output formats.
 * <p>
 * The {@code checkouts} command was implemented using picocli.
 *
 * @author Maurits Silvis
 * @since 0.1.0
 */
@Command(
        name = "checkouts",
        versionProvider = Version.class,
        mixinStandardHelpOptions = true,
        header = {"Generate a darts checkout table.", ""},
        descriptionHeading = "%n",
        description = {
                "Determine all possible darts checkouts and generate checkout tables for any range of scores.",
                "",
                "  darts checkouts 20 21",
                "  darts checkouts -i double -j double 501 501",
                "  darts checkouts -o html 1 4",
                "  darts checkouts -b quadro 901 901"
        },
        parameterListHeading = "%nParameters:%n",
        optionListHeading = "%nOptions:%n",
        sortSynopsis = false,
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
class CheckoutsCommand implements Runnable {
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
            description = "The check-in mode. Supported values: any, master, double.",
            paramLabel = "<check-in>",
            defaultValue = "any",
            order = 1
    )
    private CheckMode checkInMode;

    @Option(
            names = {"-j", "--checkout"},
            description = "The checkout mode. Supported values: any, master, double.",
            paramLabel = "<checkout>",
            defaultValue = "double",
            order = 2
    )
    private CheckMode checkoutMode;

    @Option(
            names = {"-n", "--throws"},
            description = "The number of throws. Set this value to find checkouts with a fixed number of throws.",
            paramLabel = "<throws>",
            defaultValue = "0",
            order = 3
    )
    private int numThrows;

    @Option(
            names = {"-m", "--throw-mode"},
            description = "The throw mode. Supported values: optimal, fixed. The latter value only applies when the number of throws is fixed.",
            paramLabel = "<throw mode>",
            defaultValue = "optimal",
            order = 4
    )
    private ThrowMode throwMode;

    @Option(
            names = {"-f", "--finder"},
            description = "The checkout finder type. Supported values: descending, Cartesian.",
            paramLabel = "<finder>",
            defaultValue = "descending",
            order = 5
    )
    private FinderType finderType;

    @Option(
            names = {"-t", "--table"},
            description = "The checkout table type. Supported values: ascending.",
            paramLabel = "<table>",
            hidden = true,
            defaultValue = "ascending",
            order = 6
    )
    private TableType tableType;

    @Option(
            names = {"-o", "--output"},
            description = "The output format. Supported values: Markdown, JSON, HTML, string.",
            paramLabel = "<output>",
            defaultValue = "Markdown",
            order = 7
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

    /**
     * Creates a new {@code CheckoutsCommand} object.
     */
    CheckoutsCommand() {
    }

    @Override
    public void run() {
        validate();

        Settings settings = TableSettingsBuilder.create()
                .setBoardType(boardType)
                .setCheckInMode(checkInMode)
                .setCheckoutMode(checkoutMode)
                .setNumThrows(numThrows)
                .setThrowMode(throwMode)
                .setFinderType(finderType)
                .setTableType(tableType)
                .build();

        TableGenerator tableGenerator = TableGeneratorFactory.create(tableType, settings);

        Table table = tableGenerator.generate(minScore, maxScore);

        Serializer<Table> serializer = TableSerializerFactory.create(outputFormat);
        String output = serializer.serialize(table);

        commandSpec.commandLine().getOut().println(output.strip());
    }

    private void validate() {
        if (numThrows < 0) {
            throw new ParameterException(
                    commandSpec.commandLine(),
                    "Invalid value for option '--throws': must be a non-negative integer but was '%d'"
                            .formatted(numThrows)
            );
        }

        if (maxScore < minScore) {
            throw new ParameterException(
                    commandSpec.commandLine(),
                    """
                            Invalid value for positional parameter at index 1 (<maximum>): must not be smaller \
                            than positional parameter at index 0 (<minimum>) but was '%d'"""
                            .formatted(maxScore)
            );
        }
    }
}
