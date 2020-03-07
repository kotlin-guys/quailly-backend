import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        jcenter()
        gradlePluginPortal()
        mavenCentral()
    }
    dependencies {
        classpath(Libs.kotlinStdlib)
    }
}

plugins {
    kotlin("jvm") version Vers.kotlin apply false
    id("org.springframework.boot") version Vers.springBoot
}

subprojects {
    group = "ru.kpfu.itis.quailly"

    repositories {
        jcenter()
        mavenCentral()
    }

    tasks {
        withType<KotlinCompile> {
            kotlinOptions.jvmTarget = "8"
            kotlinOptions.allWarningsAsErrors = true
        }
    }
}

repositories {
    mavenCentral()
}
