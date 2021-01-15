package mr.cell.kotlinpetclinic.http

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class GlobalExceptionHandler {
    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun handleException(req: HttpServletRequest, ex: Exception): HttpError {
        LOGGER.error("GlobalExceptionHandler captured an exception", ex)
        return HttpError(req.requestURI, ex.localizedMessage)
    }
}