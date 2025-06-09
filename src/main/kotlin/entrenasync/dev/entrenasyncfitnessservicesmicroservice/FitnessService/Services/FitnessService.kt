package entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessService.Services

import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessService.Dto.FitenessServiceResponse
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessService.Dto.FitnessServiceCreateRequest
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessService.Dto.FitnessServiceUpdateRequest
import org.bson.types.ObjectId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FitnessService {
    fun getServices(pageable: Pageable): Page<FitenessServiceResponse>
    fun getServiceById(id: ObjectId): FitenessServiceResponse
    fun getServiceByName(name: String): FitenessServiceResponse
    fun saveService(service: FitnessServiceCreateRequest): FitenessServiceResponse
    fun updateService(id: ObjectId, service: FitnessServiceUpdateRequest): FitenessServiceResponse
    fun deleteService(id: ObjectId)
}