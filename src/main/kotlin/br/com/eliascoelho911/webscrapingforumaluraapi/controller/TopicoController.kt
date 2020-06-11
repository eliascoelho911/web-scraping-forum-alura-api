package br.com.eliascoelho911.webscrapingforumaluraapi.controller

import br.com.eliascoelho911.webscrapingforumaluraapi.model.Topico
import br.com.eliascoelho911.webscrapingforumaluraapi.repository.TopicoRepository
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/topicos")
class TopicoController @Autowired constructor(
        private val topicoRepository: TopicoRepository
) {

    @GetMapping
    fun topicos(@RequestParam(required = true) pagina: Int,
                @ApiParam(value = "Access api/v1/filtrostopico to discover the filters available")
                @RequestParam(required = false) filtroTopicos: String?): List<Topico> {
        return topicoRepository.buscaTodosOsTopicos(pagina, filtroTopicos ?: "todos")
    }

    @GetMapping("/categoria")
    fun topicosPorCategoria(@RequestParam(required = true) pagina: Int,
                            @ApiParam(value = "Access api/v1/categorias to discover the categories available (use field 'value')")
                            @RequestParam(required = true) value: String,
                            @ApiParam(value = "Access api/v1/filtrostopico to discover the filters available")
                            @RequestParam(required = false) filtroTopicos: String?)
            : List<Topico> {
        return topicoRepository.buscaTopicosPorCategoria(value, pagina, filtroTopicos ?: "todos")
    }

    @GetMapping("/subcategoria")
    fun topicosPorSubcategoria(@RequestParam(required = true) pagina: Int,
                               @ApiParam(value = "Access api/v1/subcategorias to discover the subcategories available (use field 'value')")
                               @RequestParam(required = true) value: String,
                               @ApiParam(value = "Access api/v1/filtrostopico to discover the filters available")
                               @RequestParam(required = false) filtroTopicos: String?)
            : List<Topico> {
        return topicoRepository.buscaTopicosPorSubcategoria(value, pagina, filtroTopicos ?: "todos")
    }
}