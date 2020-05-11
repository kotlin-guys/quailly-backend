package ru.kpfu.itis.quailly.egg.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.kpfu.itis.quailly.egg.security.token.TokenAuthentication
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux

@EnableSwagger2WebFlux
@Configuration
open class SwaggerConfiguration {

    @Bean
    open fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .ignoredParameterTypes(TokenAuthentication::class.java)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
    }
}