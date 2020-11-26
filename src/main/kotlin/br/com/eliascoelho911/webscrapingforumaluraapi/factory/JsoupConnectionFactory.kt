package br.com.eliascoelho911.webscrapingforumaluraapi.factory

import br.com.eliascoelho911.webscrapingforumaluraapi.util.URL_FORUM
import org.jsoup.Connection
import org.jsoup.Jsoup

class JsoupConnectionFactory {
    fun forumConnection(): Connection = Jsoup.connect(URL_FORUM)
    fun createConnection(url: String): Connection = Jsoup.connect(url)
}