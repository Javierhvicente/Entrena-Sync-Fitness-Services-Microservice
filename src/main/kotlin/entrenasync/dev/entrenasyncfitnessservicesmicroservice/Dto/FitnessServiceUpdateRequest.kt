package entrenasync.dev.entrenasyncfitnessservicesmicroservice.Dto

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank

class FitnessServiceUpdateRequest(
    @field:Min(value = 3, message = "Minimum value for service name must be 3")
    @field:Max(value =40, message = "Maximum value for service name must be 40")
    @field:NotBlank(message = "Service full name must not be empty")
    val name: String,
    @field:Min(value = 1, message = "Minimum price must be 1")
    @field:Max(value = 250, message = "Maximum price must be 250")
    val price: Double,
    @field:NotBlank(message = "Service description must not be empty")
    @field:Min(value = 10, message = "Minimum description characters must be 1")
    @field:Max(value = 250, message = "Maximum description characters must be 5")
    val description: String,

    val time: String?,

    @field:Min(value = 5, message = "Minimum location characters must be 5")
    @field:Max(value = 100, message = "Maximum location characters must be 100")
    val location: String?,
){
}