package br.com.eliascoelho911.webscrapingforumaluraapi.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/filtrostopico")
class FiltroTopicosController {
    @GetMapping
    fun filtrosTopico() : List<String> {
        return listOf("todos", "sem-resposta", "sem-solucao")
    }
}