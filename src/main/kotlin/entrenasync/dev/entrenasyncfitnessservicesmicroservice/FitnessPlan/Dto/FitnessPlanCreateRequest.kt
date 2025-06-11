package entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Dto

import jakarta.validation.constraints.NotBlank

data class FitnessPlanCreateRequest(
    @NotBlank(message = "Service plan price must not be empty")
    val price: Double,
    @NotBlank(message = "Client ID must not be empty")
    val clientId: String,
    @NotBlank(message = "Service ID must not be empty")
    val serviceId: String,
    @field:NotBlank(message = "Service description must not be empty")
    val description: String,
    @NotBlank(message = "Service plan name must not be empty")
    val type: Types
)

enum class Types {
        UNIQUE,
        MENSUAL
}
