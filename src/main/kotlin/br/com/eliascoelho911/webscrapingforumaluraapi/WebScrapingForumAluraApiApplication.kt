package br.com.eliascoelho911.webscrapingforumaluraapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
@EnableSwagger2
class WebScrapingForumAluraApiApplication

fun main(args: Array<String>) {
	runApplication<WebScrapingForumAluraApiApplication>(*args)
}
