package br.com.eliascoelho911.webscrapingforumaluraapi.config.swagger

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

@Configuration
class SwaggerConfigurations {
    @Bean
    fun forumAluraApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.eliascoelho911.webscrapingforumaluraapi"))
                .paths(PathSelectors.ant("/**"))
                .build()
    }
}