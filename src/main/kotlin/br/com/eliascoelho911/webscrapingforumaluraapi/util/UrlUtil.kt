package br.com.eliascoelho911.webscrapingforumaluraapi.util

import br.com.eliascoelho911.webscrapingforumaluraapi.model.ConstantesEnderecos

class UrlUtil {
    companion object {
        fun colocaUrlBase(url: String): String {
            return "${ConstantesEnderecos.urlBase}${url.substring(1)}"
        }
    }
}