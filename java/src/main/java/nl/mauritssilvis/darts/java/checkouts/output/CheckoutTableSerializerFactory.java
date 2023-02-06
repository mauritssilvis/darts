/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.checkouts.output;

import nl.mauritssilvis.darts.java.output.Serializer;
import nl.mauritssilvis.darts.java.settings.OutputFormat;
import nl.mauritssilvis.darts.java.tables.CheckoutTable;

/**
 * A dartboard serializer factory.
 * <p>
 * Relevant design patterns: helper, parameterized static factory method.
 */
public final class CheckoutTableSerializerFactory {
    private CheckoutTableSerializerFactory() {
    }

    /**
     * Returns a new checkout table {@code Serializer} with the specified output
     * format.
     *
     * @param outputFormat the output format
     * @return a new checkout table {@code Serializer} with the specified output
     * format.
     */
    public static Serializer<CheckoutTable> create(OutputFormat outputFormat) {
        return switch (outputFormat) {
            case STRING -> StringCheckoutTableSerializer.create();
        };
    }
}
