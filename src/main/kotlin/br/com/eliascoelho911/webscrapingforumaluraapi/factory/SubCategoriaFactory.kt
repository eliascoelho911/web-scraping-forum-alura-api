package br.com.eliascoelho911.webscrapingforumaluraapi.factory

import br.com.eliascoelho911.webscrapingforumaluraapi.model.Subcategoria
import br.com.eliascoelho911.webscrapingforumaluraapi.util.UrlUtil
import org.jsoup.nodes.Element

class SubCategoriaFactory {
    fun criar(topicoElement: Element) : Subcategoria {
        return try {
            val subcategoriaElement = topicoElement.select("ol.topic-breadCrumb-list > li > a")[1]
            val urlLocal = subcategoriaElement.attr("href")
            Subcategoria(subcategoriaElement.text(),
                    UrlUtil().colocaUrlBase(urlLocal),
                    urlLocal.split("/")[2])
        } catch (e : IndexOutOfBoundsException) {
            Subcategoria("", "", "")
        }
    }
}