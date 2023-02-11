/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.factory;

import nl.mauritssilvis.darts.java.settings.BoardType;
import nl.mauritssilvis.darts.java.settings.CheckType;
import nl.mauritssilvis.darts.java.settings.FinderType;
import nl.mauritssilvis.darts.java.settings.TableType;
import nl.mauritssilvis.darts.java.tables.TableGenerator;
import nl.mauritssilvis.darts.java.tables.map.AscendingTableGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TableGeneratorFactoryTests {
    @Test
    void getAnAscendingTableGenerator() {
        TableType tableType = TableType.ASCENDING;
        BoardType boardType = BoardType.LONDON;
        CheckType checkInType = CheckType.ANY;
        CheckType checkoutType = CheckType.MASTER;
        FinderType finderType = FinderType.DESCENDING;

        TableGenerator tableGenerator = TableGeneratorFactory.create(
                tableType, boardType, checkInType, checkoutType, finderType
        );

        Assertions.assertTrue(tableGenerator instanceof AscendingTableGenerator);
    }
}