plugins {
    kotlin("jvm")
    id("maven-publish")
}

group = "io.github.nishikigii.kvia"
version = "0.0.0"

dependencies {

    // Kotlin SDK
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1-Beta")

}

publishing {
    repositories {
        mavenLocal()
    }
    publications {
        create<MavenPublication>("local") {
            from(components["java"])
            groupId = project.group as String
            artifactId = project.name
            version = project.version as String
        }
    }
}