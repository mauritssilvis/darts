/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

rootProject.name = "java-darts-core"

val apiDir = "../../api/java-darts-api"

if (file(apiDir).isDirectory) {
    includeBuild(apiDir)
}

