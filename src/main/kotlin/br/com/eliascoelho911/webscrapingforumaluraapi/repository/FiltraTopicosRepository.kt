package br.com.eliascoelho911.webscrapingforumaluraapi.repository

import org.springframework.stereotype.Repository

@Repository
class FiltraTopicosRepository {
    fun buscaFiltraTopicos(): List<String> {
        return listOf("todos", "sem-resposta", "sem-solucao")
    }
}