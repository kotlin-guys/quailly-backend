import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        jcenter()
        gradlePluginPortal()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-stdlib:1.3.61")
    }
}

plugins {
    kotlin("jvm") version "1.3.61" apply false
    id("org.springframework.boot") version "2.2.5.RELEASE"
}

subprojects {
    group = "ru.kpfu.itis.quailly"

    repositories {
        jcenter()
        mavenCentral()
    }

    tasks {
        withType<KotlinCompile> {
            kotlinOptions.jvmTarget = "11"
            kotlinOptions.allWarningsAsErrors = true
        }
    }
}

repositories {
    mavenCentral()
}