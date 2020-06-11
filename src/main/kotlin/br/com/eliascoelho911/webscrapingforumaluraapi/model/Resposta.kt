package br.com.eliascoelho911.webscrapingforumaluraapi.model

import java.time.LocalDateTime

data class Resposta(val dataCriacao: LocalDateTime, val mensagem: String,
                    val autor: Autor, val solucao: Boolean)
