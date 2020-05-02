package ru.kpfu.itis.quailly.egg.config

import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DefaultDSLContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class JooqConfiguration {

    @Bean
    open fun dslContext(): DSLContext = DefaultDSLContext(SQLDialect.POSTGRES)
}