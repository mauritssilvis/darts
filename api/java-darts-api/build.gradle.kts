plugins {
    java
}

group = "nl.mauritssilvis.darts.java"
version = "0.4.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")
}

tasks {
    test {
        useJUnitPlatform()
    }
}
