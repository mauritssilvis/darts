/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

plugins {
    `java-library`
    `maven-publish`
    signing
    alias(libs.plugins.io.github.gradle.nexus.publish.plugin)
}

group = "nl.mauritssilvis.darts.java"
version = libs.versions.java.darts.get()

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(libs.lombok)

    annotationProcessor(libs.lombok)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(libs.versions.java.jdk.get()))
        vendor.set(JvmVendorSpec.ADOPTIUM)
    }

    withSourcesJar()
    withJavadocJar()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            pom {
                name.set("Java darts API")
                description.set("A Java API for darts, a computational toolbox aimed at the game of darts")
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

signing {
    useGpgCmd() // Use the local gpg command
    sign(publishing.publications["mavenJava"])
}

nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
        }
    }
}

tasks {
    compileJava {
        options.javaModuleVersion.set("${project.version}")
    }

    jar {
        manifest {
            attributes(
                    "Implementation-Title" to project.name,
                    "Implementation-Version" to project.version,
            )
        }
    }

    javadoc {
        options.memberLevel = JavadocMemberLevel.PACKAGE
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc> {
    options.encoding = "UTF-8"
}
