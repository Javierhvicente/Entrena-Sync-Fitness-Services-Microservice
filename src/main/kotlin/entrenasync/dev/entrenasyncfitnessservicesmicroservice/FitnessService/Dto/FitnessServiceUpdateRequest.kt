package entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessService.Dto

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

class FitnessServiceUpdateRequest(
    @field:Size(min = 3, max = 40, message = "Service name must be between 3 and 40 characters")
    @field:NotBlank(message = "Service full name must not be empty")
    val name: String?,

    @field:Min(value = 1, message = "Minimum price must be 1")
    @field:Max(value = 250, message = "Maximum price must be 250")
    val price: Double?,

    @field:Size(min = 10, max = 250, message = "Service description must be between 10 and 250 characters")
    val description: String?,

    val time: String?,

    @field:Size(min = 5, max = 100, message = "Service location must be between 5 and 100 characters")
    val location: String?,
){
}