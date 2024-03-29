/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
    id("de.fayard.refreshVersions") version "0.60.3"
}

rootProject.name = "java-darts-cli"

val apiDir = "../../api/java-darts-api"

if (file(apiDir).isDirectory) {
    includeBuild(apiDir)
}

val coreDir = "../../core/java-darts-core"

if (file(coreDir).isDirectory) {
    includeBuild(coreDir)
}
