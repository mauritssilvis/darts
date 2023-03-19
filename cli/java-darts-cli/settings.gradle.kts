/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

rootProject.name = "java-darts-cli"

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.4.0"
}

val apiDir = "../../api/java-darts-api"

if (file(apiDir).isDirectory) {
    includeBuild(apiDir)
    println("Included build in directory '${apiDir}'")
} else {
    println("Directory '${apiDir}' not found")
}
