/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.types;

import nl.mauritssilvis.darts.java.api.settings.Settings;
import nl.mauritssilvis.darts.java.api.settings.TableType;
import nl.mauritssilvis.darts.java.api.tables.Table;
import nl.mauritssilvis.darts.java.api.tables.TableGenerator;
import nl.mauritssilvis.darts.java.settings.types.TableSettingsBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TableGeneratorFactoryTests {
    @Test
    void getAnAscendingTableGenerator() {
        TableType tableType = TableType.ASCENDING;

        Settings settings = TableSettingsBuilder.create()
                .setTableType(tableType)
                .build();

        TableGenerator tableGenerator = TableGeneratorFactory.create(tableType, settings);

        Assertions.assertTrue(tableGenerator instanceof AscendingTableGenerator);
    }

    @Test
    void passOnTheSettings() {
        TableType tableType = TableType.ASCENDING;

        Settings settings = TableSettingsBuilder.create()
                .setTableType(tableType)
                .build();

        TableGenerator tableGenerator = TableGeneratorFactory.create(tableType, settings);

        int minScore = 0;
        int maxScore = 0;

        Table table = tableGenerator.generate(minScore, maxScore);

        Assertions.assertEquals(settings, table.getSettings());
    }
}
