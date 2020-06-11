package br.com.eliascoelho911.webscrapingforumaluraapi.model

import java.time.LocalDateTime


data class Topico(val titulo: String, val categoria: Categoria,
                  val subcategoria: Subcategoria,
                  val mensagem: String, val autor: Autor,
                  val dataCriacao: LocalDateTime, val respostas: List<Resposta>,
                  val url: String)