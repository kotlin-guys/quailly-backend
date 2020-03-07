object Vers {
    const val kotlin = "1.3.70"

    const val springBoot = "2.2.5.RELEASE"
    const val springCloud = "Hoxton.SR1"
    const val springR2dbc = "0.1.0.M3"

    const val postgres = "42.2.9"
    const val postgresR2dbc = "0.8.1.RELEASE"
    const val liquibase = "3.8.5"

    const val reactorTest = "3.3.2.RELEASE"
    const val jacksonKotlin = "2.10.3"
}

object Libs {
    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Vers.kotlin}"
    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Vers.kotlin}"

    const val springR2dbc = "org.springframework.boot.experimental:spring-boot-starter-data-r2dbc:${Vers.springR2dbc}"
    const val springDataJpa = "org.springframework.boot:spring-boot-starter-data-jpa:${Vers.springBoot}"
    const val springWebflux = "org.springframework.boot:spring-boot-starter-webflux:${Vers.springBoot}"
    const val springZookeeper = "org.springframework.cloud:spring-cloud-starter-zookeeper-discovery:${Vers.springBoot}"
    const val springMongoDbReactive = "org.springframework.boot:spring-boot-starter-data-mongodb-reactive:${Vers.springBoot}"
    const val springConfigProcessor = "org.springframework.boot:spring-boot-configuration-processor:${Vers.springBoot}"

    const val springTest = "org.springframework.boot:spring-boot-starter-test:${Vers.springBoot}"

    const val jacksonKotlin = "com.fasterxml.jackson.module:jackson-module-kotlin:${Vers.jacksonKotlin}"
    const val postgres = "org.postgresql:postgresql:${Vers.postgres}"
    const val postgresR2dbc = "io.r2dbc:r2dbc-postgresql:${Vers.postgresR2dbc}"
    const val liquibase = "org.liquibase:liquibase-core:${Vers.liquibase}"

    const val reactorTest = "io.projectreactor:reactor-test:${Vers.reactorTest}"
}
