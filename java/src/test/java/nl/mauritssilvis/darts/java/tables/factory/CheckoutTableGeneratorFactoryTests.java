/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.factory;

import nl.mauritssilvis.darts.java.settings.BoardType;
import nl.mauritssilvis.darts.java.settings.CheckType;
import nl.mauritssilvis.darts.java.settings.FinderType;
import nl.mauritssilvis.darts.java.settings.GeneratorType;
import nl.mauritssilvis.darts.java.tables.CheckoutTableGenerator;
import nl.mauritssilvis.darts.java.tables.map.MapBasedCheckoutTableGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CheckoutTableGeneratorFactoryTests {
    @Test
    void getAMapBasedCheckoutTableGenerator() {
        GeneratorType generatorType = GeneratorType.MAP;
        BoardType boardType = BoardType.LONDON;
        CheckType checkInType = CheckType.ANY;
        CheckType checkoutType = CheckType.MASTER;
        FinderType finderType = FinderType.DESCENDING;

        CheckoutTableGenerator checkoutTableGenerator = CheckoutTableGeneratorFactory.create(
                generatorType, boardType, checkInType, checkoutType, finderType
        );

        Assertions.assertTrue(checkoutTableGenerator instanceof MapBasedCheckoutTableGenerator);
    }
}
