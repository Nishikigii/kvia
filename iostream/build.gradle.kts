plugins {
    kotlin("jvm")
}

group = "io.github.nishikigii.kvia"
version = "0.0.0"


dependencies {

    // Nishikigii SDK
    api( project(":basic") )
    // Kotlin SDK
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1-Beta")

}