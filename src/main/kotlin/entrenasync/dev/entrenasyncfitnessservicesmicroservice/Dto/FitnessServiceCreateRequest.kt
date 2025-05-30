package entrenasync.dev.entrenasyncfitnessservicesmicroservice.Dto

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

class FitnessServiceCreateRequest(
    @field:NotBlank(message = "Service full name must not be empty")
    @field:Size(min = 3, max = 40, message = "Service name must be between 3 and 40 characters")
    val name: String,

    @field:Min(value = 1, message = "Minimum price must be 1")
    @field:Max(value = 250, message = "Maximum price must be 250")
    val price: Double,

    @field:NotBlank(message = "Service description must not be empty")
    @field:Size(min = 10, max = 250, message = "Description must be between 10 and 250 characters")
    val description: String,

    val time: String?,

    @field:Size(min = 5, max = 100, message = "Location must be between 5 and 100 characters")
    val location: String?
)