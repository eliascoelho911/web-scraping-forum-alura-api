package br.com.eliascoelho911.webscrapingforumaluraapi.controller

import br.com.eliascoelho911.webscrapingforumaluraapi.repository.FiltraTopicosRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/filtrostopico")
class FiltroTopicosController @Autowired constructor(
        private val repository: FiltraTopicosRepository
){
    @GetMapping
    fun filtrosTopico() : List<String> {
        return repository.buscaFiltraTopicos()
    }
}