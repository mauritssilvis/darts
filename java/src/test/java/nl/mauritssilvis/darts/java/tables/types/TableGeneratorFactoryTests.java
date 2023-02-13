/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.types;

import nl.mauritssilvis.darts.java.settings.Settings;
import nl.mauritssilvis.darts.java.settings.TableType;
import nl.mauritssilvis.darts.java.settings.tables.TableSettingsBuilder;
import nl.mauritssilvis.darts.java.tables.TableGenerator;
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
}
