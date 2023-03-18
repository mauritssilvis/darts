/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

rootProject.name = "java-darts-cli"

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.4.0"
}

include("java-darts-api")
project(":java-darts-api").projectDir = file("../../api/java-darts-api")

