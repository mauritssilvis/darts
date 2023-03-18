/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.api.settings;

/**
 * Checkout table settings.
 * <p>
 * Relevant design patterns: immutable interface.
 *
 * @author Maurits Silvis
 * @since 0.1.0
 */
public interface Settings {
    /**
     * Gets the dartboard type.
     *
     * @return the dartboard type
     */
    BoardType getBoardType();

    /**
     * Gets the check-in mode.
     *
     * @return the check-in mode
     */
    CheckMode getCheckInMode();

    /**
     * Gets the checkout mode.
     *
     * @return the checkout mode
     */
    CheckMode getCheckoutMode();

    /**
     * Determines whether the number of throws was fixed.
     *
     * @return a boolean that signals whether the number of throws was fixed
     */
    boolean hasFixedNumThrows();

    /**
     * Gets the number of throws.
     * <p>
     * If the number of throws was fixed, the return value is 1 or more.
     * Otherwise, the return value is 0.
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
