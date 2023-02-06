/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.checkouts.output;

import nl.mauritssilvis.darts.java.output.Serializer;
import nl.mauritssilvis.darts.java.settings.OutputFormat;
import nl.mauritssilvis.darts.java.tables.CheckoutTable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CheckoutTableSerializerFactoryTests {
    @Test
    void getAStringCheckoutTableSerializer() {
        OutputFormat outputFormat = OutputFormat.STRING;
        Serializer<CheckoutTable> serializer = CheckoutTableSerializerFactory.create(outputFormat);

        Assertions.assertTrue(serializer instanceof StringCheckoutTableSerializer);
    }
}

