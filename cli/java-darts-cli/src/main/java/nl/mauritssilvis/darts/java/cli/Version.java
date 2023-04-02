/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.cli;

import picocli.CommandLine.IVersionProvider;

import java.lang.module.ModuleDescriptor;
import java.util.Optional;

/**
 * An implementation of the {@code IVersionProvider} class that returns the
 * current version of the Java-based command-line toolbox {@code darts} with a
 * copyright notice.
 *
 * @author Maurits Silvis
 * @since 0.1.0
 */
class Version implements IVersionProvider {
    /**
     * Creates a new {@code Version} object.
     */
    Version() {
    }

    @Override
    public String[] getVersion() {
        String title = "java-darts-cli";
        String version = "";

        ModuleDescriptor moduleDescriptor = getClass().getModule().getDescriptor();

        if (moduleDescriptor != null) {
            Optional<ModuleDescriptor.Version> optional = moduleDescriptor.version();

            if (optional.isPresent()) {
                version = optional.get().toString();
            }
        }

        return new String[]{
                title + (!version.isBlank() ? "-" : "") + version,
                "Copyright © 2023 Maurits Silvis",
                "SPDX-License-Identifier: GPL-3.0-or-later"
        };
    }
}
