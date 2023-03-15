/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.cli;

import picocli.CommandLine.IVersionProvider;

/**
 * An implementation of the {@code IVersionProvider} class that returns the
 * current version of the Java-based command-line toolbox {@code darts} with a
 * copyright notice.
 *
 * @author Maurits Silvis
 * @since 0.1.0
 */
class Version implements IVersionProvider {
    @Override
    public String[] getVersion() {
        return new String[]{
                "java-darts-cli 0.2.0",
                "Copyright © 2023 Maurits Silvis",
                "SPDX-License-Identifier: GPL-3.0-or-later"
        };
    }
}
