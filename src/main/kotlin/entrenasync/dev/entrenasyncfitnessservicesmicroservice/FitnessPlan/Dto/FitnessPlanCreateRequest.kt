package entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Dto

data class FitnessPlanCreateRequest(
    val price: Double,
    val clientId: String,
    val serviceId: String,
    val description: String,
    val renovation: String? = null,
    val durationInDays: Int? = null,
    val isDeleted: Boolean = false
)
