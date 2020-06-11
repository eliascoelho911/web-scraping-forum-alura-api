package br.com.eliascoelho911.webscrapingforumaluraapi.controller

import br.com.eliascoelho911.webscrapingforumaluraapi.model.Categoria
import br.com.eliascoelho911.webscrapingforumaluraapi.repository.CategoriaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/categorias")
class CategoriaController @Autowired constructor(
        private val categoriaRepository: CategoriaRepository) {
    @GetMapping
    fun categorias(): List<Categoria> {
        return categoriaRepository.buscaCategorias()
    }
}