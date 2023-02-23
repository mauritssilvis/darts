/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.settings;

/**
 * A checkout table settings builder.
 * <p>
 * Relevant design patterns: builder, fluent interface.
 *
 * @author Maurits Silvis
 * @since 0.1.0
 */
public interface SettingsBuilder {
    /**
     * Sets the dartboard type.
     *
     * @param boardType the dartboard type
     * @return this settings builder
     */
    SettingsBuilder setBoardType(BoardType boardType);

    /**
     * Sets the check-in mode.
     *
     * @param checkInMode the check-in mode
     * @return this settings builder
     */
    SettingsBuilder setCheckInMode(CheckMode checkInMode);

    /**
     * Sets the checkout mode.
     *
     * @param checkoutMode the checkout mode
     * @return this settings builder
     */
    SettingsBuilder setCheckoutMode(CheckMode checkoutMode);

    /**
     * Sets the number of throws.
     * <p>
     * The value 0 can be used to signal that the number of throws was not
     * fixed.
     *
     * @param numThrows the number of throws
     * @return this settings builder
     */
    SettingsBuilder setNumThrows(int numThrows);

    /**
     * Sets the throw mode.
     *
     * @param throwMode the throw mode
     * @return this settings builder
     */
    SettingsBuilder setThrowMode(ThrowMode throwMode);

    /**
     * Sets the finder type.
     *
     * @param finderType the finder type
     * @return this settings builder
     */
    SettingsBuilder setFinderType(FinderType finderType);

    /**
     * Sets the table type.
     *
     * @param tableType the table type
     * @return this settings builder
     */
    SettingsBuilder setTableType(TableType tableType);

    /**
     * Gets a new {@code Settings} object with the specified properties.
     * <p>
     * Non-null, default values have to be set for unspecified properties.
     *
     * @return a new {@code Settings} object with the specified properties
     */
    Settings build();
}
