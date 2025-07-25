package entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessService.Services

import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessService.Dto.FitenessServiceResponse
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessService.Dto.FitnessServiceCreateRequest
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessService.Dto.FitnessServiceUpdateRequest
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessService.Exceptions.FitnessServiceException
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessService.Mappers.toEntity
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessService.Mappers.toResponse
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessService.Repositories.FitnessServicesRepository
import org.bson.types.ObjectId
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
@CacheConfig(cacheNames = ["fitnessServices"])
class FitenssServiceImpl(
    private val repository: FitnessServicesRepository,
): FitnessService {
    private val log: Logger = LoggerFactory.getLogger(FitenssServiceImpl::class.java)
    override fun getServices(pageable: Pageable): Page<FitenessServiceResponse> {
        log.info("Getting all services available")
        val services = repository.findAll(pageable)
        return services.map { it.toResponse() }
    }

    @CachePut(key = "#id")
    override fun getServiceById(id: ObjectId): FitenessServiceResponse {
        log.info("Getting service by id: $id")
        return repository.findById(id)
            .orElseThrow { FitnessServiceException.FitnessServiceNotFoundException(id.toString()) }
            .toResponse()
    }

    @CachePut(key = "#result.id")
    override fun getServiceByName(name: String): FitenessServiceResponse {
        log.info("Getting service by name $name")
        return repository.findByName(name)
            ?.toResponse()
            ?: throw FitnessServiceException.FitnessServiceNotFoundException("Service with name $name not found")
    }

    @CachePut(key = "#result.id")
    override fun saveService(service: FitnessServiceCreateRequest): FitenessServiceResponse {
        log.info("Saving Service")
        val newService = service.toEntity()
        return repository.save(newService).toResponse()
    }

    @CachePut(key = "#id")
    override fun updateService(id: ObjectId, service: FitnessServiceUpdateRequest): FitenessServiceResponse {
        log.info("Updating Service wirh id $id")
        val serviceToUpdate = repository.findById(id).orElseThrow{ FitnessServiceException.FitnessServiceNotFoundException(id.toString())}
        val updatedService = service.toEntity(serviceToUpdate)
        return repository.save(updatedService).toResponse()
    }

    @CacheEvict(key = "#id")
    override fun deleteService(id: ObjectId) {
        log.info("Deleting Service with id $id")
        repository.deleteById(id)
    }


}