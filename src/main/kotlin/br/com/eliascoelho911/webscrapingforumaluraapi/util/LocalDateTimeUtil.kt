package br.com.eliascoelho911.webscrapingforumaluraapi.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeUtil {
    companion object {
        fun cria(dateTime: String): LocalDateTime {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
        }
    }
}