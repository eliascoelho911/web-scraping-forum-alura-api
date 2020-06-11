package br.com.eliascoelho911.webscrapingforumaluraapi.factory

import br.com.eliascoelho911.webscrapingforumaluraapi.extension.paraString
import br.com.eliascoelho911.webscrapingforumaluraapi.model.*
import br.com.eliascoelho911.webscrapingforumaluraapi.util.LocalDateTimeUtil
import br.com.eliascoelho911.webscrapingforumaluraapi.util.UrlUtil
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.lang.IndexOutOfBoundsException

class TopicoFactory {
    companion object {
        fun criar(topicoElement: Element): Topico {
            val url = buscaUrlDoTopico(topicoElement)
            val titulo = buscaTituloDoTopico(topicoElement)
            val categoria = buscaCategoriaDoTopico(topicoElement)
            val subcategoria = buscaSubCategoriaDoTopico(topicoElement)
            val autor = AutorFactory.criar(topicoElement)
            val dataCriacao = buscaDataDeCriacaodoTopico(topicoElement)

            val bodyTopico = Jsoup.connect(url).get().body()
            val mensagem = buscaMensagemDoTopico(bodyTopico)
            val respostas = buscaRespostasDoTopico(bodyTopico)

            return Topico(titulo, categoria, subcategoria, mensagem, autor, dataCriacao, respostas, url)
        }

        private fun buscaRespostasDoTopico(bodyTopico: Element): MutableList<Resposta> {
            val respostas = mutableListOf<Resposta>()
            buscaRespostasElementDoTopico(bodyTopico).forEach { respostaElement ->
                val solucao = verificaSeRespostaEhSolucao(respostaElement)
                val mensagem = buscaResposta(respostaElement)
                val dataDeCriacaoResposta = LocalDateTimeUtil.cria(respostaElement.select("time")[0]
                        .attr("datetime"))
                val autor = buscaAutorDaResposta(respostaElement)
                respostas.add(Resposta(dataDeCriacaoResposta, mensagem, autor, solucao))
            }

            return respostas
        }

        private fun buscaAutorDaResposta(respostaElement: Element): Autor {
            val autorElement = respostaElement.select("section.topic-post-author > div > a")
            return Autor(autorElement.text(), UrlUtil.colocaUrlBase(autorElement.attr("href")))
        }

        private fun buscaResposta(respostaElement: Element) =
                respostaElement.select("section.topic-post-content")[0].select("p, pre").paraString()

        private fun verificaSeRespostaEhSolucao(respostaElement: Element) =
                respostaElement.select("div.topic-post-solved").size == 1

        private fun buscaRespostasElementDoTopico(bodyTopico: Element) =
                bodyTopico.select("section.topic-answers > section")

        private fun buscaMensagemDoTopico(bodyTopico: Element) =
                bodyTopico.select("section.topic-post-content")[0].select("p, pre").paraString()

        private fun buscaDataDeCriacaodoTopico(element: Element) =
                LocalDateTimeUtil.cria(element.select("time")[0].attr("datetime"))

        private fun buscaCategoriaDoTopico(element: Element): Categoria {
            val categoriaElement = element.select("ol.topic-breadCrumb-list > li > a")[0]
            val urlLocal = categoriaElement.attr("href")
            return Categoria(categoriaElement.text(),
                    UrlUtil.colocaUrlBase(urlLocal),
                    urlLocal.split("/")[2])
        }

        private fun buscaSubCategoriaDoTopico(element: Element): Subcategoria {
            return try {
                val subcategoriaElement = element.select("ol.topic-breadCrumb-list > li > a")[1]
                val urlLocal = subcategoriaElement.attr("href")
                Subcategoria(subcategoriaElement.text(),
                        UrlUtil.colocaUrlBase(urlLocal),
                        urlLocal.split("/")[2])
            } catch (e : IndexOutOfBoundsException) {
                Subcategoria("", "", "")
            }
        }

        private fun buscaTituloDoTopico(element: Element) =
                element.select("a.forumList-item-subject-info-title-link")[0].text()

        private fun buscaUrlDoTopico(element: Element) = element.attr("itemId")
    }
}