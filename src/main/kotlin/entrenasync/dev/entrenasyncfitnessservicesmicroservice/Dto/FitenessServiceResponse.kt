package entrenasync.dev.entrenasyncfitnessservicesmicroservice.Dto

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.time.LocalDateTime

class FitenessServiceResponse(

    var id: String,

    @field:Size(min = 3, max = 40, message = "Service name must be between 3 and 40 characters")
    @field:NotBlank(message = "Service full name must not be empty")
    val name: String,

    @field:Min(value = 1, message = "Minimum price must be 1")
    @field:Max(value = 250, message = "Maximum price must be 250")
    val price: Double,

    @field:NotBlank(message = "Service avatar must not be empty")
    val avatar: String,

    @field:NotBlank(message = "Service description must not be empty")
    @field:Size(min = 10, max = 250, message = "Description must be between 10 and 250 characters")
    val description: String,

    val time: String?,

    @field:Size(min = 5, max = 100, message = "location must be between 5 and 100 characters")
    val location: String?,

    val cratedAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val is_deleted: Boolean
) {
}