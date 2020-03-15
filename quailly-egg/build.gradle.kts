plugins {
    kotlin("jvm")
    id("org.springframework.boot")
    id("org.jetbrains.kotlin.plugin.noarg") version Vers.kotlin
}

repositories {
    maven(url = "https://repo.spring.io/milestone/")
    mavenCentral()
}

dependencies {
    implementation(Libs.kotlinStdlib)
    implementation(Libs.kotlinReflect)
    implementation(Libs.kotlinJdk8)
    runtimeOnly(Libs.jaxb)

    implementation(Libs.jacksonKotlin)

    implementation(Libs.springWebflux)

    implementation(Libs.postgres)
    implementation(Libs.springDataJpa)
}
