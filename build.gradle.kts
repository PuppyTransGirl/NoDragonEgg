plugins {
    id("java")
    id("com.gradleup.shadow") version "9.4.2"
}

group = "toutouchien.nodragonegg"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/") // Paper
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.6-R0.1-SNAPSHOT")
    implementation("org.bstats:bstats-bukkit:3.2.1")
}

tasks {
    named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
        archiveClassifier.set("")
        relocate("org.bstats", "${project.group}.org.bstats")
    }
    build {
        dependsOn("shadowJar")
    }
}
