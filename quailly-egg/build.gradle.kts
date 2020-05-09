import com.rohanprabhu.gradle.plugins.kdjooq.*

plugins {
    kotlin("jvm")
    id("org.springframework.boot")
    id("org.jetbrains.kotlin.plugin.noarg") version Vers.kotlin
    id("com.rohanprabhu.kotlin-dsl-jooq") version "0.4.5"
}

repositories {
    mavenCentral()
    jcenter()
    maven(url = "https://repo.spring.io/milestone/")
    maven(url = "http://oss.jfrog.org/artifactory/oss-snapshot-local/")
}

tasks.test {
    useJUnitPlatform()
}

dependencies {
    implementation(Libs.kotlinStdlib)
    implementation(Libs.kotlinReflect)
    implementation(Libs.kotlinJdk8)
    runtimeOnly(Libs.jaxb)

    jooqGeneratorRuntime(Libs.postgres)
    implementation(Libs.jooqCodegen)
    implementation(Libs.jooqMeta)
    implementation(Libs.swagger)
    implementation(Libs.swaggerUi)
    implementation(Libs.swaggerWebflux)
    implementation(Libs.jacksonKotlin)

    implementation(Libs.springWebflux)
    implementation(Libs.springSecurity)

    implementation(Libs.springJooq)
    implementation(Libs.postgres)

    testImplementation(Libs.springTest) {
        exclude("org.junit.vintage", "junit-vintage-engine")
    }
}

jooqGenerator {
    jooqEdition = JooqEdition.OpenSource
    jooqVersion = Vers.jooq
    configuration("primary", sourceSets.getByName("main")) {
        configuration = jooqCodegenConfiguration {
            jdbc {
                username = System.getenv("SPRING_DATASOURCE_USERNAME") ?: "quailly"
                password = System.getenv("SPRING_DATASOURCE_PASSWORD") ?: "quailly"
                driver = "org.postgresql.Driver"
                url = System.getenv("SPRING_DATASOURCE_URL") ?: "jdbc:postgresql://localhost:5432/quailly"
            }

            generator {
                target {
                    packageName = "ru.kpfu.itis.quailly.egg.repository.jooq.schema"
                    directory = "${project.buildDir}/generated/jooq/primary"
                    isClean = true
                }

                database {
                    name = "org.jooq.meta.postgres.PostgresDatabase"
                    inputSchema = "public"
                }
            }
        }
    }
}