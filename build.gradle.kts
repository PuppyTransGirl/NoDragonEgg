plugins {
    id("java")
    id("com.gradleup.shadow") version "9.4.2"
}

group = "toutouchien.nodragonegg"
version = "1.0.0"

repositories {
    maven("https://repo.papermc.io/repository/maven-public/") // Paper
    mavenCentral()
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.6-R0.1-SNAPSHOT")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

tasks {
    compileJava {
        options.debugOptions.debugLevel = "none"
    }

    processResources {
        filteringCharset = "UTF-8"

        from(rootProject.files("LICENSE", "THIRD_PARTY_LICENSES.md")) {
            into("META-INF")
        }
    }

    named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
        archiveClassifier.set("")
    }
}
