plugins {
    kotlin("jvm")
}

group = "io.github.nishikigii.kvia"
version = "0.0.0"


dependencies {
    api( project(":basic") )
    api( project(":iostream") )
    api( project(":fsys") )
}