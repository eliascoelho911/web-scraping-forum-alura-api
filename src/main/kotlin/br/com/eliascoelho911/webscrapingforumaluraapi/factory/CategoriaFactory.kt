package br.com.eliascoelho911.webscrapingforumaluraapi.factory

import br.com.eliascoelho911.webscrapingforumaluraapi.model.Categoria
import br.com.eliascoelho911.webscrapingforumaluraapi.util.UrlUtil
import org.jsoup.nodes.Element

class CategoriaFactory {
    fun criar(topicoElement: Element) : Categoria {
        val categoriaElement = topicoElement.select("ol.topic-breadCrumb-list > li > a")[0]
        val urlLocal = categoriaElement.attr("href")
        return Categoria(categoriaElement.text(),
                UrlUtil().colocaUrlBase(urlLocal),
                urlLocal.split("/")[2])
    }
}