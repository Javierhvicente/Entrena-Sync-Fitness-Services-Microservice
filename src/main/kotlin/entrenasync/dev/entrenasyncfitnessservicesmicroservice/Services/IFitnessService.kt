package entrenasync.dev.entrenasyncfitnessservicesmicroservice.Services

import entrenasync.dev.entrenasyncfitnessservicesmicroservice.Dto.FitenessServiceResponse
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.Dto.FitnessServiceCreateRequest
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.Dto.FitnessServiceUpdateRequest
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.Models.FitnessService
import org.bson.types.ObjectId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface IFitnessService {
    fun getServices(pageable: Pageable): Page<FitenessServiceResponse>
    fun getServiceById(id: ObjectId): FitenessServiceResponse?
    fun getServiceByName(name: String): FitenessServiceResponse?
    fun saveService(service: FitnessServiceCreateRequest): FitenessServiceResponse
    fun updateService(id: ObjectId, service: FitnessServiceUpdateRequest): FitenessServiceResponse?
    fun deleteService(id: ObjectId)
}