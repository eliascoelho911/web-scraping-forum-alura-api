package br.com.eliascoelho911.webscrapingforumaluraapi.util

const val URL_BASE = "http://cursos.alura.com.br/"
const val URL_FORUM = "${URL_BASE}forum/"
class UrlUtil {
    fun colocaUrlBase(url: String) = "$URL_BASE${url.substring(1)}"
    fun colocaUrlForum(url: String) = "$URL_FORUM$url"
}