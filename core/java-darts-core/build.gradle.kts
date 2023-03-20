/*
 * Copyright © 2023 Maurits Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

plugins {
    `java-library`
    `maven-publish`
    signing
    id("io.github.gradle-nexus.publish-plugin") version "1.3.0"
}

group = "nl.mauritssilvis.darts.java"
version = "0.6.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.26")

    annotationProcessor("org.projectlombok:lombok:1.18.26")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.2")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")

    api("nl.mauritssilvis.darts.java:java-darts-api:${project.version}")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
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
                name.set("Java darts core")
                description.set("A Java implementation of darts, a computational toolbox aimed at the game of darts")
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
    processResources {
        dependsOn("copyResources")
    }

    test {
        useJUnitPlatform()
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

tasks.register<Copy>("copyResources") {
    from(projectDir)
    into(sourceSets.main.get().output.resourcesDir.toString())
    include("LICENSE.md", "README.md")
}
