plugins {
    kotlin("jvm") version "1.9.20"
    id("maven-publish")
}

group = "io.github.nishikigii"
version = "0.0.0"


subprojects {

    repositories {
        mavenLocal()
        maven("https://mirrors.ustc.edu.cn/public")
        maven("https://maven.aliyun.com/repository/public")
        mavenCentral()
    }

}