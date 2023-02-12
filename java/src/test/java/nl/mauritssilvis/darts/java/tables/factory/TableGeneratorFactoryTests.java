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
        CheckMode checkInMode = CheckMode.ANY;
        CheckMode checkoutMode = CheckMode.MASTER;
        int numThrows = -1;
        ThrowMode throwMode = ThrowMode.OPTIMAL;
        FinderType finderType = FinderType.DESCENDING;

        TableGenerator tableGenerator = TableGeneratorFactory.create(
                tableType, boardType, checkInMode, checkoutMode, numThrows, throwMode, finderType
        );

        Assertions.assertTrue(tableGenerator instanceof AscendingTableGenerator);
    }
}
