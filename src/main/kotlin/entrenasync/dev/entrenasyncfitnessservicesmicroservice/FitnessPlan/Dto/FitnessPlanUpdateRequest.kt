package entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Dto

data class FitnessPlanUpdateRequest(
    val price: Double? = null,
    val renovation: String? = null,
    val isDeleted: Boolean? = null
)
