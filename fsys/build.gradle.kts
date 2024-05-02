plugins {
    kotlin("jvm")
}

group = "io.github.nishikigii.kiva"
version = "0.0.0"


dependencies {
    api( project(":basic") )
    api( project(":iostream") )
}