/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.settings;

/**
 * Checkout table settings.
 * <p>
 * Relevant design patterns: immutable interface.
 */
public interface TableSettings {
    /**
     * Gets the dartboard type.
     *
     * @return the dartboard type
     */
    BoardType getBoardType();

    /**
     * Gets the check-in type.
     *
     * @return the check-in type
     */
    CheckType getCheckInType();

    /**
     * Gets the checkout type.
     *
     * @return the checkout type
     */
    CheckType getCheckoutType();

    /**
     * Determines whether the number of throws was fixed.
     *
     * @return a boolean that signals whether the number of throws was fixed.
     */
    boolean hasFixedNumThrows();

    /**
     * Gets the number of throws.
     * <p>
     * If the number of throws was fixed, the return value is 1 or more.
     * Otherwise, the return value is -1.
     *
     * @return the number of throws
     */
    int getNumThrows();

    /**
     * Gets the throw mode.
     *
     * @return the throw mode
     */
    ThrowMode getThrowMode();

    /**
     * Gets the finder type.
     *
     * @return the finder type
     */
    FinderType getFinderType();

    /**
     * Gets the table type.
     *
     * @return the table type
     */
    TableType getTableType();
}
