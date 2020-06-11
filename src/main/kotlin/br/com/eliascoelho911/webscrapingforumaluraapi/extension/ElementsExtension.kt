package br.com.eliascoelho911.webscrapingforumaluraapi.extension

import org.jsoup.select.Elements

fun Elements.paraString(): String {
    return this.joinToString(separator = "\n", transform = {
        it.text()
    })
}