/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.settings;

/**
 * A checkout table settings builder.
 * <p>
 * Relevant design patterns: builder, fluent interface.
 */
public interface TableSettingsBuilder {
    /**
     * Sets the dartboard type.
     *
     * @param boardType the dartboard type
     * @return this settings builder
     */
    TableSettingsBuilder setBoardType(BoardType boardType);

    /**
     * Sets the check-in type.
     *
     * @param checkInType the check-in type
     * @return this settings builder
     */
    TableSettingsBuilder setCheckInType(CheckType checkInType);

    /**
     * Sets the checkout type.
     *
     * @param checkoutType the checkout type
     * @return this settings builder
     */
    TableSettingsBuilder setCheckoutType(CheckType checkoutType);

    /**
     * Sets the number of throws.
     * <p>
     * The value -1 can be used to signal that the number of throws was not
     * fixed.
     *
     * @param numThrows the number of throws
     * @return this settings builder
     */
    TableSettingsBuilder setNumThrows(int numThrows);

    /**
     * Sets the throw mode.
     *
     * @param throwMode the throw mode
     * @return this settings builder
     */
    TableSettingsBuilder setThrowMode(ThrowMode throwMode);

    /**
     * Sets the finder type.
     *
     * @param finderType the finder type
     * @return this settings builder
     */
    TableSettingsBuilder setFinderType(FinderType finderType);

    /**
     * Sets the table type.
     *
     * @param tableType the table type
     * @return this settings builder
     */
    TableSettingsBuilder setTableType(TableType tableType);

    /**
     * Gets a new {@TableSettings} object with the specified properties.
     * <p>
     * Non-null, default values have to be set for unspecified properties.
     *
     * @return a new {@TableSettings} object with the specified properties.
     */
    TableSettings build();
}
