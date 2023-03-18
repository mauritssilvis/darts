/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

plugins {
    application
    `maven-publish`
    signing
    id("io.github.gradle-nexus.publish-plugin") version "1.3.0"
}

group = "nl.mauritssilvis.darts.java"
version = "0.4.0-SNAPSHOT"

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

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            pom {
                name.set("Java darts CLI")
                description.set("A Java-based command-line toolbox aimed at the game of darts")
                url.set("https://github.com/mauritssilvis/darts")

                licenses {
                    license {
                        name.set("GNU General Public License v3.0 or later")
                        url.set("https://spdx.org/licenses/GPL-3.0-or-later.html")
                    }
                }

                developers {
                    developer {
                        id.set("mauritssilvis")
                        name.set("Maurits Silvis")
                        email.set("mauritssilvis@gmail.com")
                    }
                }

                scm {
                    connection.set("scm:git:ssh://git@github.com:mauritssilvis/darts.git")
                    developerConnection.set("scm:git:ssh://git@github.com:mauritssilvis/darts.git")
                    url.set("https://github.com/mauritssilvis/darts")
                }
            }
        }
    }

    repositories {
        maven {
            name = "buildFolder"
            url = uri(layout.buildDirectory.dir("repo"))
        }
    }
}

nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
        }
    }
}

signing {
    useGpgCmd() // Use the local gpg command
    sign(publishing.publications["mavenJava"])
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
            attributes(
                    "Implementation-Title" to "${project.name}",
                    "Implementation-Version" to "${project.version}",
                    "Main-Class" to "nl.mauritssilvis.darts.java.cli.DartsApp"
            )
        }
    }

    javadoc {
        options.memberLevel = JavadocMemberLevel.PACKAGE
    }

    installDist {
        doLast {
            copy {
                from(destinationDir)
                into(".")
            }
        }
    }
}

tasks.register<Delete>("cleanDist") {
    file("bin").deleteRecursively()
    file("lib").deleteRecursively()
}