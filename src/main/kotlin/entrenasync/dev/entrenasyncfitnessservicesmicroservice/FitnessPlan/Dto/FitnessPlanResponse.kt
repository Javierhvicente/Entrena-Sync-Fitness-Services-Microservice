package entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Dto

data class FitnessPlanResponse(
    val id: String,
    val price: Double,
    val clientId: String,
    val serviceId: String,
    val description: String,
    val createdAt: String,
    val renovation: String?,
    val isDeleted: Boolean = false
)
