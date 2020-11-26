package br.com.eliascoelho911.webscrapingforumaluraapi.factory

import br.com.eliascoelho911.webscrapingforumaluraapi.extension.paraString
import br.com.eliascoelho911.webscrapingforumaluraapi.model.Autor
import br.com.eliascoelho911.webscrapingforumaluraapi.model.Resposta
import br.com.eliascoelho911.webscrapingforumaluraapi.util.LocalDateTimeUtil
import br.com.eliascoelho911.webscrapingforumaluraapi.util.UrlUtil
import org.jsoup.nodes.Element

class RespostasFactory {
    fun criar(bodyTopico: Element) : List<Resposta> {
        val respostas = mutableListOf<Resposta>()
        buscaRespostasElementDoTopico(bodyTopico).forEach { respostaElement ->
            val solucao = verificaSeRespostaEhSolucao(respostaElement)
            val mensagem = buscaResposta(respostaElement)
            val dataDeCriacaoResposta = LocalDateTimeUtil().cria(respostaElement.select("time")[0]
                    .attr("datetime"))
            val autor = buscaAutorDaResposta(respostaElement)
            respostas.add(Resposta(dataDeCriacaoResposta, mensagem, autor, solucao))
        }

        return respostas
    }

    private fun buscaResposta(respostaElement: Element) =
            respostaElement.select("section.topic-post-content")[0].select("p, pre").paraString()

    private fun verificaSeRespostaEhSolucao(respostaElement: Element) =
            respostaElement.select("div.topic-post-solved").size == 1

    private fun buscaRespostasElementDoTopico(bodyTopico: Element) =
            bodyTopico.select("section.topic-answers > section")

    private fun buscaAutorDaResposta(respostaElement: Element): Autor {
        val autorElement = respostaElement.select("section.topic-post-author > div > a")
        return Autor(autorElement.text(), UrlUtil().colocaUrlBase(autorElement.attr("href")))
    }
}