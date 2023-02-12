/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.factory;

import nl.mauritssilvis.darts.java.settings.*;
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
     * @param numThrows    the number of throws if fixed and -1 otherwise
     * @param throwMode    the throw mode
     * @param finderType   the checkout finder type
     * @return a new {@code TableGenerator} for tables of the specified type
     */
    public static TableGenerator create(
            TableType tableType,
            BoardType boardType,
            CheckType checkInType,
            CheckType checkoutType,
            int numThrows,
            ThrowMode throwMode,
            FinderType finderType
    ) {
        return switch (tableType) {
            case ASCENDING -> AscendingTableGenerator.of(
                    boardType, checkInType, checkoutType, numThrows, throwMode, finderType
            );
        };
    }
}
