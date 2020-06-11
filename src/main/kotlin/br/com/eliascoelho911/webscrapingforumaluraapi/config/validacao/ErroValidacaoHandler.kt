package br.com.eliascoelho911.webscrapingforumaluraapi.config.validacao

import org.jsoup.HttpStatusException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.Exception

@RestControllerAdvice
class ErroValidacaoHandler @Autowired constructor(
        private val messageSource: MessageSource
) {
    @ResponseStatus(code = BAD_REQUEST)
    @ExceptionHandler(HttpStatusException::class)
    fun handler(exception: HttpStatusException): String {
        return "${exception.localizedMessage} - ${exception.url}"
    }

    @ResponseStatus(code = BAD_REQUEST)
    @ExceptionHandler(Exception::class)
    fun handler(exception: Exception): String {
        return exception.localizedMessage.toString()
    }
}