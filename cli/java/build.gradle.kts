/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

plugins {
    application
}

group = "nl.mauritssilvis.darts.java"
version = "0.1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.26")

    annotationProcessor("org.projectlombok:lombok:1.18.26")
    annotationProcessor("info.picocli:picocli-codegen:4.7.1")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.2")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")

    implementation("info.picocli:picocli:4.7.1")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
        vendor.set(JvmVendorSpec.ADOPTIUM)
    }

    withSourcesJar()
    withJavadocJar()
}

application {
    applicationName = "darts"
    mainClass.set("nl.mauritssilvis.darts.java.cli.DartsApp")
}

tasks {
    compileJava {
        // Set the location for files generated by picocli
        options.compilerArgs.add("-Aproject=${project.group}/${project.name}")
    }

    test {
        useJUnitPlatform()
    }

    jar {
        manifest {
            attributes("Main-Class" to "nl.mauritssilvis.darts.java.cli.DartsApp")
        }
    }

    javadoc {
        options.memberLevel = JavadocMemberLevel.PACKAGE
    }
}