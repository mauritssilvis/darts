/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

plugins {
    id("application")
}

group = "nl.mauritssilvis.darts.java"
version = "0.1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.24")

    annotationProcessor("org.projectlombok:lombok:1.18.24")
    annotationProcessor("info.picocli:picocli-codegen:4.7.1")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.1")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.1")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.1")

    implementation("info.picocli:picocli:4.7.1")
}

test {
    useJUnitPlatform()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }

    withSourcesJar()
    withJavadocJar()
}

javadoc {
    options.memberLevel = JavadocMemberLevel.PACKAGE
}

application {
    applicationName = "darts"
    mainClass = "nl.mauritssilvis.darts.java.cli.DartsApp"
}

jar {
    manifest {
        attributes("Main-Class": "nl.mauritssilvis.darts.java.cli.DartsApp")
    }
}
