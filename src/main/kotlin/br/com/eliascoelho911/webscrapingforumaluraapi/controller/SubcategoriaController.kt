package br.com.eliascoelho911.webscrapingforumaluraapi.controller

import br.com.eliascoelho911.webscrapingforumaluraapi.model.CategoriaCompleta
import br.com.eliascoelho911.webscrapingforumaluraapi.repository.SubcategoriaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/subcategorias")
class SubcategoriaController @Autowired constructor(
        private val subcategoriaRepository: SubcategoriaRepository) {
    @GetMapping
    fun subCategorias(): List<CategoriaCompleta> {
        return subcategoriaRepository.buscaSubcategorias()
    }
}