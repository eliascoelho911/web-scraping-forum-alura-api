package br.com.eliascoelho911.webscrapingforumaluraapi.factory

import br.com.eliascoelho911.webscrapingforumaluraapi.extension.paraString
import br.com.eliascoelho911.webscrapingforumaluraapi.model.*
import br.com.eliascoelho911.webscrapingforumaluraapi.util.LocalDateTimeUtil
import br.com.eliascoelho911.webscrapingforumaluraapi.util.UrlUtil
import org.jsoup.nodes.Element

class TopicoFactory {

    private val localDateTimeUtil: LocalDateTimeUtil by lazy {
        LocalDateTimeUtil()
    }

    fun criar(topicoElement: Element): Topico {
        val url = buscaUrlDoTopico(topicoElement)
        val titulo = buscaTituloDoTopico(topicoElement)
        val categoria = buscaCategoriaDoTopico(topicoElement)
        val subcategoria = buscaSubCategoriaDoTopico(topicoElement)
        val autor = buscaAutorDoTopico(topicoElement)
        val dataCriacao = buscaDataDeCriacaodoTopico(topicoElement)

        val bodyTopico = JsoupConnectionFactory().createConnection(url).get().body()
        val mensagem = buscaMensagemDoTopico(bodyTopico)
        val respostas = buscaRespostasDoTopico(bodyTopico)

        return Topico(titulo, categoria, subcategoria, mensagem, autor, dataCriacao, respostas, url)
    }

    private fun buscaRespostasDoTopico(bodyTopico: Element): List<Resposta> {
        return RespostasFactory().criar(bodyTopico)
    }

    private fun buscaAutorDoTopico(topicoElement: Element) = AutorFactory().criar(topicoElement)

    private fun buscaMensagemDoTopico(bodyTopico: Element) =
            bodyTopico.select("section.topic-post-content")[0].select("p, pre").paraString()

    private fun buscaDataDeCriacaodoTopico(element: Element) =
            localDateTimeUtil.cria(element.select("time")[0].attr("datetime"))

    private fun buscaCategoriaDoTopico(element: Element): Categoria {
        return CategoriaFactory().criar(element)
    }

    private fun buscaSubCategoriaDoTopico(element: Element): Subcategoria {
        return SubCategoriaFactory().criar(element)
    }

    private fun buscaTituloDoTopico(element: Element) =
            element.select("a.forumList-item-subject-info-title-link")[0].text()

    private fun buscaUrlDoTopico(element: Element) = element.attr("itemId")
}