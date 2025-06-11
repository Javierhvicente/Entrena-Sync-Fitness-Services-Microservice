package entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Mappers

import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Dto.FitnessPlanCreateRequest
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Dto.FitnessPlanResponse
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Dto.FitnessPlanUpdateRequest
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Dto.Types
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Models.FitnessPlan
import org.bson.types.ObjectId
import java.time.LocalDate

fun FitnessPlan.toResponse(): FitnessPlanResponse {
    return FitnessPlanResponse(
        id = id.toString(),
        description = description,
        price = price,
        clientId = clientId,
        serviceId = serviceId,
        createdAt = createdAt.toString(),
        renovation = renovation?.toString(),
    )
}

fun FitnessPlanCreateRequest.toEntity(): FitnessPlan {
    val createdAt = LocalDate.now()
    val renovationDate = if (type == Types.MENSUAL) createdAt.plusMonths(1) else null

    return FitnessPlan(
        id = ObjectId.get(),
        description = description,
        price = price,
        clientId = clientId,
        serviceId = serviceId,
        renovation = renovationDate,
        createdAt = createdAt
    )
}


fun FitnessPlanUpdateRequest.toEntity(existingPlan: FitnessPlan): FitnessPlan {
    return FitnessPlan(
        id = existingPlan.id,
        clientId = existingPlan.clientId,
        serviceId = existingPlan.serviceId,
        description =existingPlan.description,
        createdAt = existingPlan.createdAt,
        price = this.price ?: existingPlan.price,
        renovation = this.renovation?.let { LocalDate.parse(it) } ?: existingPlan.renovation,
        isDeleted = this.isDeleted ?: existingPlan.isDeleted
    )
}