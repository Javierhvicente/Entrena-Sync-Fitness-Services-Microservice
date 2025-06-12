package entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessService.Mappers

import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessService.Dto.FitenessServiceResponse
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessService.Dto.FitnessServiceCreateRequest
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessService.Dto.FitnessServiceUpdateRequest
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessService.Models.FitnessService
import org.bson.types.ObjectId
import java.time.LocalDateTime

fun FitnessService.toResponse(): FitenessServiceResponse {
    return FitenessServiceResponse(
        id = id.toString(),
        name = name,
        description = description,
        price = price,
        time = time,
        location = location,
        avatar = avatar,
        cratedAt = cratedAt,
        updatedAt = updatedAt,
        type = type.toString(),
        is_deleted = is_deleted
    )
}

fun FitnessServiceCreateRequest.toEntity(): FitnessService {
    return FitnessService(
        id = ObjectId.get(),
        name = name,
        description = description,
        price = price,
        time = time,
        location = location,
        type = type,
    )
}

fun FitnessServiceUpdateRequest.toEntity(oldService: FitnessService): FitnessService {
    return FitnessService(
        id = oldService.id,
        name = name?: oldService.name,
        description = description?: oldService.description,
        price = price?: oldService.price,
        time = time?: oldService.time,
        location = location?: oldService.location,
        type = oldService.type,
        updatedAt = LocalDateTime.now()
    )
}