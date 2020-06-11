package br.com.eliascoelho911.webscrapingforumaluraapi.factory

import br.com.eliascoelho911.webscrapingforumaluraapi.model.Autor
import br.com.eliascoelho911.webscrapingforumaluraapi.model.ConstantesEnderecos
import br.com.eliascoelho911.webscrapingforumaluraapi.util.UrlUtil
import org.jsoup.nodes.Element

class AutorFactory {
    companion object {
        fun criar(topicoElement: Element): Autor {
            val urlRetornada = topicoElement.select("a.forumList-item-info")
                    .attr("href")
            val urlCorrigida = if (urlRetornada.isEmpty()) {
                ConstantesEnderecos.urlBase
            } else {
                UrlUtil.colocaUrlBase(urlRetornada)
            }
            return Autor(topicoElement.select("p.forumList-item-info-name > strong").text(),
                    urlCorrigida)
        }
    }
}