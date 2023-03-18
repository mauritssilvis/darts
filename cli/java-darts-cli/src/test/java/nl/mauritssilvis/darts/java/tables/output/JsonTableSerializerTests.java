/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.output;

import nl.mauritssilvis.darts.java.api.output.Serializer;
import nl.mauritssilvis.darts.java.api.settings.*;
import nl.mauritssilvis.darts.java.api.tables.Table;
import nl.mauritssilvis.darts.java.api.tables.TableGenerator;
import nl.mauritssilvis.darts.java.settings.TableSettingsBuilder;
import nl.mauritssilvis.darts.java.tables.TableGeneratorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class JsonTableSerializerTests {
    @Test
    void getASerializedTable() {
        TableType tableType = TableType.ASCENDING;
        BoardType boardType = BoardType.YORKSHIRE;
        CheckMode checkoutMode = CheckMode.ANY;

        Settings settings = TableSettingsBuilder.create()
                .setBoardType(boardType)
                .setCheckoutMode(checkoutMode)
                .setTableType(tableType)
                .build();

        TableGenerator tableGenerator = TableGeneratorFactory.create(tableType, settings);

        int minScore = 22;
        int maxScore = 23;

        Table table = tableGenerator.generate(minScore, maxScore);

        Serializer<Table> serializer = JsonTableSerializer.create();

        String output = serializer.serialize(table);

        Assertions.assertEquals(
                """
                        {
                            "22": {
                                "multiplicity": 1,
                                "checkouts": [
                                    {
                                        "throws": [
                                            [
                                                "D11"
                                            ]
                                        ],
                                        "multiplicity": 1
                                    }
                                ]
                            },
                            "23": {
                                "multiplicity": 38,
                                "checkouts": [
                                    {
                                        "throws": [
                                            [
                                                "D11"
                                            ],
                                            [
                                                "1"
                                            ]
                                        ],
                                        "multiplicity": 2
                                    },
                                    {
                                        "throws": [
                                            [
                                                "20",
                                                "D10"
                                            ],
                                            [
                                                "3"
                                            ]
                                        ],
                                        "multiplicity": 4
                                    },
                                    {
                                        "throws": [
                                            [
                                                "19"
                                            ],
                                            [
                                                "4",
                                                "D2"
                                            ]
                                        ],
                                        "multiplicity": 4
                                    },
                                    {
                                        "throws": [
                                            [
                                                "18",
                                                "D9"
                                            ],
                                            [
                                                "5"
                                            ]
                                        ],
                                        "multiplicity": 4
                                    },
                                    {
                                        "throws": [
                                            [
                                                "17"
                                            ],
                                            [
                                                "6",
                                                "D3"
                                            ]
                                        ],
                                        "multiplicity": 4
                                    },
                                    {
                                        "throws": [
                                            [
                                                "16",
                                                "D8"
                                            ],
                                            [
                                                "7"
                                            ]
                                        ],
                                        "multiplicity": 4
                                    },
                                    {
                                        "throws": [
                                            [
                                                "15"
                                            ],
                                            [
                                                "8",
                                                "D4"
                                            ]
                                        ],
                                        "multiplicity": 4
                                    },
                                    {
                                        "throws": [
                                            [
                                                "14",
                                                "D7"
                                            ],
                                            [
                                                "9"
                                            ]
                                        ],
                                        "multiplicity": 4
                                    },
                                    {
                                        "throws": [
                                            [
                                                "13"
                                            ],
                                            [
                                                "10",
                                                "D5"
                                            ]
                                        ],
                                        "multiplicity": 4
                                    },
                                    {
                                        "throws": [
                                            [
                                                "12",
                                                "D6"
                                            ],
                                            [
                                                "11"
                                            ]
                                        ],
                                        "multiplicity": 4
                                    }
                                ]
                            }
                        }
                        """,
                output);
    }

    @ParameterizedTest
    @EnumSource(TableType.class)
    void getTheTableStart(TableType tableType) {
        Settings settings = TableSettingsBuilder.create()
                .setTableType(tableType)
                .build();

        TableGenerator tableGenerator = TableGeneratorFactory.create(tableType, settings);

        int minScore = 2;
        int maxScore = 2;

        Table table = tableGenerator.generate(minScore, maxScore);

        Serializer<Table> serializer = JsonTableSerializer.create();

        String output = serializer.serialize(table);

        Assertions.assertTrue(output.startsWith("{\n    \"2\": {"));
    }

    @ParameterizedTest
    @MethodSource("withAnEmptySerializedObject")
    void getAnEmptySerializedObject(int numThrows, ThrowMode throwMode) {
        TableType tableType = TableType.ASCENDING;

        Settings settings = TableSettingsBuilder.create()
                .setNumThrows(numThrows)
                .setThrowMode(throwMode)
                .setTableType(tableType)
                .build();

        TableGenerator tableGenerator = TableGeneratorFactory.create(tableType, settings);

        int minScore = 1;
        int maxScore = 0;

        Table table = tableGenerator.generate(minScore, maxScore);

        Serializer<Table> serializer = JsonTableSerializer.create();

        Assertions.assertEquals("{\n}\n", serializer.serialize(table));
    }

    private static Stream<Arguments> withAnEmptySerializedObject() {
        return Stream.of(
                Arguments.of(0, ThrowMode.OPTIMAL),
                Arguments.of(0, ThrowMode.FIXED),
                Arguments.of(1, ThrowMode.OPTIMAL),
                Arguments.of(2, ThrowMode.FIXED)
        );
    }

    @ParameterizedTest
    @MethodSource("withAnEmptySerializedSubObject")
    void getAnEmptySerializedSubObject(int numThrows, ThrowMode throwMode) {
        TableType tableType = TableType.ASCENDING;

        Settings settings = TableSettingsBuilder.create()
                .setNumThrows(numThrows)
                .setThrowMode(throwMode)
                .setTableType(tableType)
                .build();

        TableGenerator tableGenerator = TableGeneratorFactory.create(tableType, settings);

        int score = 0;

        Table table = tableGenerator.generate(score, score);

        Serializer<Table> serializer = JsonTableSerializer.create();

        String output = """
                {
                    "0": {
                        "multiplicity": 0,
                        "checkouts": [
                        ]
                    }
                }
                """;

        Assertions.assertEquals(output, serializer.serialize(table));
    }

    private static Stream<Arguments> withAnEmptySerializedSubObject() {
        return Stream.of(
                Arguments.of(0, ThrowMode.OPTIMAL),
                Arguments.of(0, ThrowMode.FIXED),
                Arguments.of(1, ThrowMode.OPTIMAL),
                Arguments.of(10, ThrowMode.FIXED)
        );
    }
}
