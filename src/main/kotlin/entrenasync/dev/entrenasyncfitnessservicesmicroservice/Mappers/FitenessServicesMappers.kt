package entrenasync.dev.entrenasyncfitnessservicesmicroservice.Mappers

import entrenasync.dev.entrenasyncfitnessservicesmicroservice.Dto.FitenessServiceResponse
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.Dto.FitnessServiceCreateRequest
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.Dto.FitnessServiceUpdateRequest
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.Models.FitnessService
import org.bson.types.ObjectId
import java.time.LocalDateTime

fun FitnessService.toResponse(): FitenessServiceResponse{
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
        is_deleted = is_deleted
    )
}

fun FitnessServiceCreateRequest.toEntity(): FitnessService{
    return FitnessService(
        id = ObjectId.get(),
        name = name,
        description = description,
        price = price,
        time = time,
        location = location,
    )
}

fun FitnessServiceUpdateRequest.toEntity(oldService: FitnessService): FitnessService{
    return FitnessService(
        id = oldService.id,
        name = name?: oldService.name,
        description = description?: oldService.description,
        price = price?: oldService.price,
        time = time?: oldService.time,
        location = location?: oldService.location,
        updatedAt = LocalDateTime.now()
    )
}