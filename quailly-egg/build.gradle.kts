
plugins {
    kotlin("jvm")
    id("org.springframework.boot")
}

repositories {
    maven(url = "https://repo.spring.io/milestone/")
    mavenCentral()
}

dependencies {
    implementation(Libs.kotlinStdlib)

    implementation(Libs.springWebflux)
    implementation(Libs.springR2dbc)
    implementation(Libs.postgresR2dbc)
}
