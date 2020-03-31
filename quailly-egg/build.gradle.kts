plugins {
    kotlin("jvm")
    id("org.springframework.boot")
    id("org.jetbrains.kotlin.plugin.noarg") version Vers.kotlin
}

repositories {
    mavenCentral()
    jcenter()
    maven(url = "https://repo.spring.io/milestone/")
    maven(url = "http://oss.jfrog.org/artifactory/oss-snapshot-local/")
}

dependencies {
    implementation(Libs.kotlinStdlib)
    implementation(Libs.kotlinReflect)
    implementation(Libs.kotlinJdk8)
    runtimeOnly(Libs.jaxb)
    implementation(Libs.swagger)
    implementation(Libs.swaggerUi)
    implementation(Libs.jacksonKotlin)

    implementation(Libs.springWeb)
    implementation(Libs.springHalExplorer)
    implementation(Libs.springHateoas)
    implementation(Libs.springRestDocs)

    implementation(Libs.springDataJpa)
    implementation(Libs.postgres)

    testImplementation(Libs.springTest)
    testImplementation(Libs.springRestDocs)
}
