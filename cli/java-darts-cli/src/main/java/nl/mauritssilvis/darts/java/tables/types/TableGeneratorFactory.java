/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.types;

import nl.mauritssilvis.darts.java.settings.Settings;
import nl.mauritssilvis.darts.java.settings.TableType;
import nl.mauritssilvis.darts.java.tables.TableGenerator;

/**
 * A checkout table generator factory.
 * <p>
 * Relevant design patterns: helper, parameterized static factory method.
 *
 * @author Maurits Silvis
 * @since 0.1.0
 */
public final class TableGeneratorFactory {
    private TableGeneratorFactory() {
    }

    /**
     * Returns a new {@code TableGenerator} for tables of the specified type
     * with the specified settings.
     *
     * @param tableType the table type
     * @param settings  the table settings
     * @return a new {@code TableGenerator} for tables of the specified type
     * with the specified settings
     */
    public static TableGenerator create(TableType tableType, Settings settings) {
        return switch (tableType) {
            case ASCENDING -> AscendingTableGenerator.of(settings);
        };
    }
}
