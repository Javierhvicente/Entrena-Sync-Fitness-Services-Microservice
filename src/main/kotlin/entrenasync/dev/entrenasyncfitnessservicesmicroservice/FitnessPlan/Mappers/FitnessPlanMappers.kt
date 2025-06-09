package entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Mappers

import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Dto.FitnessPlanCreateRequest
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Dto.FitnessPlanResponse
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Dto.FitnessPlanUpdateRequest
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
        durationInDays = durationInDays ?: 0,
        renovation = renovation?.toString(),
    )
}

fun FitnessPlanCreateRequest.toEntity(): FitnessPlan {
    return FitnessPlan(
        id = ObjectId.get(),
        description = description,
        price = price,
        clientId = clientId,
        serviceId = serviceId,
        durationInDays = durationInDays,
        renovation = renovation?.let { LocalDate.parse(it) },
        createdAt = LocalDate.now()
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
        durationInDays = this.durationInDays ?: existingPlan.durationInDays,
        is_deleted = this.isDeleted ?: existingPlan.is_deleted
    )
}