/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.cli;

import picocli.CommandLine.IVersionProvider;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

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
        String title = "java-darts-cli";
        String version = "";

        Attributes attributes = getManifestAttributes(title);

        if (!attributes.isEmpty()) {
            title = attributes.getValue("Implementation-Title");
            version = attributes.getValue("Implementation-Version");
        }

        return new String[]{
                title + " " + version,
                "Copyright © 2023 Maurits Silvis",
                "SPDX-License-Identifier: GPL-3.0-or-later"
        };
    }

    private Attributes getManifestAttributes(String title) {
        Attributes emptyAttributes = new Attributes();
        Enumeration<URL> resources;

        try {
            resources = getClass().getClassLoader().getResources("META-INF/MANIFEST.MF");
        } catch (IOException ignore) {
            return emptyAttributes;
        }

        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();

            try {
                Manifest manifest = new Manifest(url.openStream());
                Attributes mainAttributes = manifest.getMainAttributes();

                if (title.equals(mainAttributes.getValue("Implementation-Title"))) {
                    return mainAttributes;
                }
            } catch (IOException ignore) {
            }
        }

        return emptyAttributes;
    }
}
