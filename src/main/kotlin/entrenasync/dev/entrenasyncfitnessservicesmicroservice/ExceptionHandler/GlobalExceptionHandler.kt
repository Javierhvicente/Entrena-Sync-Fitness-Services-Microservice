package entrenasync.dev.entrenasyncworkermicroservice.ExceptionHandler

import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Exceptions.FitnessPlanExceptions
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import java.time.LocalDateTime

@RestControllerAdvice
class GlobalExceptionHandler {

    private val log = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(FitnessPlanExceptions.FitnessPlanNotFoundException::class)
    fun handleFitnessPlanNotFoundException(
        ex: FitnessPlanExceptions.FitnessPlanNotFoundException,
        request: WebRequest
    ): ResponseEntity<Map<String, Any>> {
        log.error("FitnessPlan not found: ${ex.message}")

        val body: Map<String, Any> = mapOf(
            "timestamp" to LocalDateTime.now(),
            "status" to HttpStatus.NOT_FOUND.value(),
            "error" to "Not Found",
            "message" to (ex.message ?: "Plan not found"),
            "path" to request.getDescription(false).removePrefix("uri=")
        )

        return ResponseEntity(body, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(FitnessPlanExceptions.CannotDeletePlanException::class)
    fun handleCannotDeletePlanException(
        ex: FitnessPlanExceptions.CannotDeletePlanException,
        request: WebRequest
    ): ResponseEntity<Map<String, Any>> {
        log.error("Cannot delete plan: ${ex.message}")

        val body: Map<String, Any> = mapOf(
            "timestamp" to LocalDateTime.now(),
            "status" to HttpStatus.BAD_REQUEST.value(),
            "error" to "Bad Request",
            "message" to (ex.message ?: "Cannot delete plan"),
            "path" to request.getDescription(false).removePrefix("uri=")
        )

        return ResponseEntity(body, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(FitnessPlanExceptions.FitnessPlanAlreadyExistsException::class)
    fun handleFitnessPlanAlreadyExistsException(
        ex: FitnessPlanExceptions.FitnessPlanAlreadyExistsException,
        request: WebRequest
    ): ResponseEntity<Map<String, Any>> {
        log.error("FitnessPlan already exists: ${ex.message}")

        val body: Map<String, Any> = mapOf(
            "timestamp" to LocalDateTime.now(),
            "status" to HttpStatus.CONFLICT.value(),
            "error" to "Conflict",
            "message" to (ex.message ?: "Plan already exists"),
            "path" to request.getDescription(false).removePrefix("uri=")
        )

        return ResponseEntity(body, HttpStatus.CONFLICT)
    }

    @ExceptionHandler(FitnessPlanExceptions::class)
    fun handleFitnessPlanExceptions(
        ex: FitnessPlanExceptions,
        request: WebRequest
    ): ResponseEntity<Map<String, Any>> {
        log.error("FitnessPlan exception: ${ex.message}")

        val body: Map<String, Any> = mapOf(
            "timestamp" to LocalDateTime.now(),
            "status" to HttpStatus.BAD_REQUEST.value(),
            "error" to "Bad Request",
            "message" to (ex.message ?: "Fitness plan error"),
            "path" to request.getDescription(false).removePrefix("uri=")
        )

        return ResponseEntity(body, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(
        ex: IllegalArgumentException,
        request: WebRequest
    ): ResponseEntity<Map<String, Any>> {
        log.error("Invalid argument: ${ex.message}")

        val body: Map<String, Any> = mapOf(
            "timestamp" to LocalDateTime.now(),
            "status" to HttpStatus.BAD_REQUEST.value(),
            "error" to "Bad Request",
            "message" to "Invalid request parameter: ${ex.message ?: "Invalid argument"}",
            "path" to request.getDescription(false).removePrefix("uri=")
        )

        return ResponseEntity(body, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    fun handleAllExceptions(
        ex: Exception,
        request: WebRequest
    ): ResponseEntity<Map<String, Any>> {
        log.error("Unexpected error: ${ex.message}", ex)

        val body: Map<String, Any> = mapOf(
            "timestamp" to LocalDateTime.now(),
            "status" to HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "error" to "Internal Server Error",
            "message" to "An unexpected error occurred",
            "path" to request.getDescription(false).removePrefix("uri=")
        )

        return ResponseEntity(body, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
