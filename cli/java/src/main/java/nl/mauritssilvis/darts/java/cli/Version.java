/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.cli;

import picocli.CommandLine.IVersionProvider;

class Version implements IVersionProvider {
    @Override
    public String[] getVersion() {
        return new String[]{
                "darts 0.1.0",
                "Copyright © 2023 Maurits Silvis",
                "SPDX-License-Identifier: GPL-3.0-or-later"
        };
    }
}
