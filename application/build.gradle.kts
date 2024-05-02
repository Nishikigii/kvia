plugins {
    kotlin("jvm")
    id("maven-publish")
}

group = "io.github.nishikigii.kvia"
version = "0.0.0"

dependencies {
    api( project(":basic") )
    testImplementation(kotlin("reflect"))
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


tasks.test {
    useJUnitPlatform()
}