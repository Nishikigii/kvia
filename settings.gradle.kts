plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "kvia"
include("application")
include("basic")
include("iostream")
include("fsys")
include("logging")
