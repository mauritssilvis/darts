/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.factory;

import nl.mauritssilvis.darts.java.settings.*;
import nl.mauritssilvis.darts.java.tables.TableGenerator;
import nl.mauritssilvis.darts.java.tables.ascending.AscendingTableGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TableGeneratorFactoryTests {
    @Test
    void getAnAscendingTableGenerator() {
        TableType tableType = TableType.ASCENDING;
        BoardType boardType = BoardType.LONDON;
        CheckType checkInType = CheckType.ANY;
        CheckType checkoutType = CheckType.MASTER;
        int numThrows = -1;
        FinderMode finderMode = FinderMode.MINIMUM;
        FinderType finderType = FinderType.DESCENDING;

        TableGenerator tableGenerator = TableGeneratorFactory.create(
                tableType, boardType, checkInType, checkoutType, numThrows, finderMode, finderType
        );

        Assertions.assertTrue(tableGenerator instanceof AscendingTableGenerator);
    }
}
