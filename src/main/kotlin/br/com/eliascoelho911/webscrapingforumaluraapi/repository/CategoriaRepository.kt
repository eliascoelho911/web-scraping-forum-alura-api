package br.com.eliascoelho911.webscrapingforumaluraapi.repository

import br.com.eliascoelho911.webscrapingforumaluraapi.model.Categoria
import br.com.eliascoelho911.webscrapingforumaluraapi.model.ConstantesEnderecos
import org.jsoup.Jsoup
import org.springframework.stereotype.Repository
import kotlin.streams.toList

@Repository
class CategoriaRepository {
    fun buscaCategorias(): List<Categoria> {
        val body = Jsoup.connect(ConstantesEnderecos.urlForum).get().body()

        val todasCategoriasElement = body
                .select("fieldset.select-filter > select > option")
        return todasCategoriasElement.stream().map {
            val categoria = it.attr("value")
            val prefixCategoria = if (categoria == "todos") {
                ""
            } else {
                "categoria-"
            }
            Categoria(it.text(),
                    "${ConstantesEnderecos.urlForum}$prefixCategoria$categoria",
                    categoria)
        }.toList()
    }
}