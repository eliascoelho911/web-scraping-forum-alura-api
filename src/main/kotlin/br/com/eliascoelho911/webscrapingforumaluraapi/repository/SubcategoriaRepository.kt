package br.com.eliascoelho911.webscrapingforumaluraapi.repository

import br.com.eliascoelho911.webscrapingforumaluraapi.factory.JsoupConnectionFactory
import br.com.eliascoelho911.webscrapingforumaluraapi.model.Categoria
import br.com.eliascoelho911.webscrapingforumaluraapi.model.CategoriaCompleta
import br.com.eliascoelho911.webscrapingforumaluraapi.model.Subcategoria
import br.com.eliascoelho911.webscrapingforumaluraapi.util.UrlUtil
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.springframework.stereotype.Repository

@Repository
class SubcategoriaRepository {

    private val urlUtil: UrlUtil by lazy {
        UrlUtil()
    }

    fun buscaSubcategorias(): List<CategoriaCompleta> {
        val body = JsoupConnectionFactory().forumConnection().get().body()
        return body.select("ul.dashboard-categoryList > li").map {
            val categoriaElement = it.select("a.dashboard-category-name-text")
            val categoria = criaCategoria(categoriaElement)
            val subCategorias = buscaSubcategorias(it)
            CategoriaCompleta(categoria, subCategorias)
        }.toList()
    }

    private fun criaCategoria(categoriaElement: Elements): Categoria {
        val urlLocal = categoriaElement.attr("href")
        return Categoria(categoriaElement.text(),
                urlUtil.colocaUrlBase(urlLocal),
                urlLocal.split("/")[2])
    }

    private fun buscaSubcategorias(element: Element): List<Subcategoria> {
        return element.select("ul.dashboard-category-subcategories-list > li").map {
            val subCategoriaElement = it.select("a")
            val urlLocal = subCategoriaElement.attr("href")
            Subcategoria(subCategoriaElement.text(),
                    urlUtil.colocaUrlBase(urlLocal),
                    urlLocal.split("/")[2])
        }
    }
}