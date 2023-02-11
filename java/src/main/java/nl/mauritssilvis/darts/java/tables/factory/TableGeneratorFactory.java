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
import nl.mauritssilvis.darts.java.tables.ascending.AscendingTableGenerator;

/**
 * A checkout table generator factory.
 * <p>
 * Relevant design patterns: helper, parameterized static factory method.
 */
public final class TableGeneratorFactory {
    private TableGeneratorFactory() {
    }

    /**
     * Returns a new {@code TableGenerator} for tables of the specified type.
     *
     * @param tableType    the table type
     * @param boardType    the dartboard type
     * @param checkInType  the check-in type
     * @param checkoutType the checkout type
     * @param finderType   the checkout finder type
     * @return a new {@code TableGenerator} for tables of the specified type
     */
    public static TableGenerator create(
            TableType tableType,
            BoardType boardType,
            CheckType checkInType,
            CheckType checkoutType,
            FinderType finderType
    ) {
        return switch (tableType) {
            case ASCENDING -> AscendingTableGenerator.of(boardType, checkInType, checkoutType, finderType);
        };
    }
}
