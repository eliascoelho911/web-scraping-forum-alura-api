package br.com.eliascoelho911.webscrapingforumaluraapi.repository

import br.com.eliascoelho911.webscrapingforumaluraapi.factory.JsoupConnectionFactory
import br.com.eliascoelho911.webscrapingforumaluraapi.factory.TopicoFactory
import br.com.eliascoelho911.webscrapingforumaluraapi.model.Topico
import br.com.eliascoelho911.webscrapingforumaluraapi.util.URL_BASE
import br.com.eliascoelho911.webscrapingforumaluraapi.util.UrlUtil
import org.jsoup.nodes.Element
import org.springframework.stereotype.Repository

@Repository
class TopicoRepository {
    private val urlUtil: UrlUtil by lazy {
        UrlUtil()
    }

    fun buscaTopicosPorCategoria(categoria: String, pagina: Int, filtroTopicos: String): List<Topico> {
        val urlSite = urlUtil.colocaUrlForum("categoria-$categoria/$pagina")
        return buscaTopicos(urlSite)
    }

    fun buscaTopicosPorSubcategoria(subcategoria: String, pagina: Int, filtroTopicos: String): List<Topico> {
        val urlSite = urlUtil.colocaUrlForum("subcategoria-$subcategoria/$filtroTopicos/$pagina")
        return buscaTopicos(urlSite)
    }

    fun buscaTodosOsTopicos(pagina: Int, filtroTopicos: String): List<Topico> {
        val urlSite = urlUtil.colocaUrlForum("$filtroTopicos/$pagina")
        return buscaTopicos(urlSite)
    }

    private fun buscaTopicos(urlSite: String): List<Topico> {
        val body = JsoupConnectionFactory().createConnection(urlSite).get().body()

        val topicosRetornados = mutableListOf<Topico>()
        buscaTopicosNaPagina(body).forEach {
            topicosRetornados.add(TopicoFactory().criar(it))
        }

        return tiraTopicosInvalidos(topicosRetornados)
    }

    private fun tiraTopicosInvalidos(topicosRetornados: MutableList<Topico>) =
            topicosRetornados.filter { it.autor.nome != "Aluno" && it.autor.url != URL_BASE }

    private fun buscaTopicosNaPagina(body: Element) =
            body.select("ul.forumList-topics > li.forumList-item")
}