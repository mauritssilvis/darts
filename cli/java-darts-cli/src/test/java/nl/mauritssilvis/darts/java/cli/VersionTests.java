/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.cli;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class VersionTests {
    @Test
    void getTheVersionStrings() {
        Version version = new Version();
        String[] strings = version.getVersion();

        Assertions.assertAll(
                () -> Assertions.assertEquals("java-darts-cli", strings[0]),
                () -> Assertions.assertEquals("Copyright © 2023 Maurits Silvis", strings[1]),
                () -> Assertions.assertEquals("SPDX-License-Identifier: GPL-3.0-or-later", strings[2])
        );
    }
}
