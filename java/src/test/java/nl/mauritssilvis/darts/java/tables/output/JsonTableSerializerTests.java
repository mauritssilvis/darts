/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.output;

import nl.mauritssilvis.darts.java.output.Serializer;
import nl.mauritssilvis.darts.java.settings.*;
import nl.mauritssilvis.darts.java.tables.Table;
import nl.mauritssilvis.darts.java.tables.TableGenerator;
import nl.mauritssilvis.darts.java.tables.factory.TableGeneratorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class JsonTableSerializerTests {
    @Test
    void getAJsonTable() {
        TableType tableType = TableType.ASCENDING;
        BoardType boardType = BoardType.YORKSHIRE;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.ANY;
        int numThrows = -1;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        FinderType finderType = FinderType.DESCENDING;

        TableGenerator tableGenerator = TableGeneratorFactory.create(
                tableType, boardType, checkInMode, checkoutMode, numThrows, throwMode, finderType
        );

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
        BoardType boardType = BoardType.LONDON;
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.DOUBLE;
        int numThrows = -1;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        FinderType finderType = FinderType.DESCENDING;

        TableGenerator tableGenerator = TableGeneratorFactory.create(
                tableType, boardType, checkInMode, checkoutMode, numThrows, throwMode, finderType
        );

        int minScore = 2;
        int maxScore = 2;

        Table table = tableGenerator.generate(minScore, maxScore);

        Serializer<Table> serializer = JsonTableSerializer.create();

        String output = serializer.serialize(table);

        Assertions.assertTrue(output.startsWith("{\n    \"2\": {"));
    }
}
