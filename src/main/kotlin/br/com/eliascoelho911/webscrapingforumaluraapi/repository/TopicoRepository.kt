package br.com.eliascoelho911.webscrapingforumaluraapi.repository

import br.com.eliascoelho911.webscrapingforumaluraapi.factory.TopicoFactory
import br.com.eliascoelho911.webscrapingforumaluraapi.model.ConstantesEnderecos.Companion.urlBase
import br.com.eliascoelho911.webscrapingforumaluraapi.model.ConstantesEnderecos.Companion.urlForum
import br.com.eliascoelho911.webscrapingforumaluraapi.model.Topico
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.springframework.stereotype.Repository

@Repository
class TopicoRepository {
    fun buscaTopicosPorCategoria(categoria: String, pagina: Int, filtroTopicos: String): List<Topico> {
        val urlSite = "$urlForum$categoria/$pagina"
        return buscaTopicos(urlSite)
    }

    fun buscaTopicosPorSubcategoria(subcategoria: String, pagina: Int, filtroTopicos: String): List<Topico> {
        val urlSite = "$urlForum$subcategoria/$filtroTopicos/$pagina"
        return buscaTopicos(urlSite)
    }

    fun buscaTodosOsTopicos(pagina: Int, filtroTopicos: String): List<Topico> {
        val urlSite = "$urlForum$filtroTopicos/$pagina"
        return buscaTopicos(urlSite)
    }

    private fun buscaTopicos(urlSite: String): List<Topico> {
        val body = Jsoup.connect(urlSite).get().body()

        val topicosRetornados = mutableListOf<Topico>()
        buscaTopicosNaPagina(body).forEach {
            topicosRetornados.add(TopicoFactory.criar(it))
        }

        return topicosRetornados.filter { it.autor.nome != "Aluno" && it.autor.url != urlBase }
    }

    private fun buscaTopicosNaPagina(body: Element) =
            body.select("ul.forumList-topics > li.forumList-item")
}