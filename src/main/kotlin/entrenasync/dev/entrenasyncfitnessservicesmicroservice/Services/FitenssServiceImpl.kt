package entrenasync.dev.entrenasyncfitnessservicesmicroservice.Services

import entrenasync.dev.entrenasyncfitnessservicesmicroservice.Dto.FitenessServiceResponse
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.Dto.FitnessServiceCreateRequest
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.Dto.FitnessServiceUpdateRequest
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.Exceptions.FitnessServiceException
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.Mappers.toEntity
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.Mappers.toResponse
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.Models.FitnessService
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.Repositories.IFitnessServicesRepository
import org.bson.types.ObjectId
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class FitenssServiceImpl(
    private val repository: IFitnessServicesRepository,
): IFitnessService {
    private val log: Logger = LoggerFactory.getLogger(FitenssServiceImpl::class.java)
    override fun getServices(pageable: Pageable): Page<FitenessServiceResponse> {
        log.info("Getting all services available")
        val services = repository.findAll(pageable)
        return services.map { services->services.toResponse() }
    }

    override fun getServiceById(id: ObjectId): FitenessServiceResponse? {
        log.info("Getting service by id: $id")
        return repository.findById(id).map { services-> services.toResponse() }?.orElseThrow{FitnessServiceException.FitnessServiceNotFoundException(
            id.toString()
        )}
    }

    override fun getServiceByName(name: String): FitenessServiceResponse? {
        log.info("Getting service by name $name")
        return repository.findByName(name)?.toResponse()?: throw FitnessServiceException.FitnessServiceNotFoundException(name)
    }

    override fun saveService(service: FitnessServiceCreateRequest): FitenessServiceResponse {
        log.info("Saving Service")
        val newService = service.toEntity()
        return repository.save(newService).toResponse()
    }

    override fun updateService(id: ObjectId, service: FitnessServiceUpdateRequest): FitenessServiceResponse? {
        log.info("Updating Service wirh id $id")
        val serviceToUpdate = repository.findById(id).orElseThrow{FitnessServiceException.FitnessServiceNotFoundException(id.toString())}
        val updatedService = service.toEntity(serviceToUpdate)
        return repository.save(updatedService).toResponse()
    }

    override fun deleteService(id: ObjectId) {
        log.info("Deleting Service with id $id")
        repository.deleteById(id)
    }


}