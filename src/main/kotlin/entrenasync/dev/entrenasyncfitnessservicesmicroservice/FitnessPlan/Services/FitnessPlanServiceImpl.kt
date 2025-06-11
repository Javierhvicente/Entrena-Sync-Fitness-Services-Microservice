package entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Services

import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Dto.FitnessPlanCreateRequest
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Dto.FitnessPlanResponse
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Dto.FitnessPlanUpdateRequest
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Exceptions.FitnessPlanExceptions
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Mappers.toEntity
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Mappers.toResponse
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Repositories.FitnessPlanRepository
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessService.Services.FitenssServiceImpl
import org.bson.types.ObjectId
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class FitnessPlanServiceImpl(private val fitnessPlanRepository: FitnessPlanRepository): FitnessPlanService {
    private val log: Logger = LoggerFactory.getLogger(FitenssServiceImpl::class.java)
    override fun getServicesByClientId(clientId: String): List<FitnessPlanResponse> {
        log.info("Getting fitness plans for client with id: $clientId")
        val services = fitnessPlanRepository.findByClientId(clientId)
        return services.map { it.toResponse() }
    }

    override fun createServicePlan(servicePlan: FitnessPlanCreateRequest): FitnessPlanResponse {
        log.info("Creating a new fitness plan for client ${servicePlan.clientId}")
        var thisClientServices = getServicesByClientId(servicePlan.clientId)
        if (thisClientServices.any { it.serviceId == servicePlan.serviceId }) {
            throw FitnessPlanExceptions.FitnessPlanAlreadyExistsException(
                serviceId = servicePlan.serviceId,
                clientId = servicePlan.clientId
            )
        }
        val newPlan = servicePlan.toEntity()
        return fitnessPlanRepository.save(newPlan).toResponse()
    }

    override fun updateServicePlan(id: ObjectId, servicePlan: FitnessPlanUpdateRequest): FitnessPlanResponse {
        log.info("updating plan with id: $id")
        val servicePlanToUpdate = fitnessPlanRepository.findById(id)
            .orElseThrow { FitnessPlanExceptions.FitnessPlanNotFoundException(id.toString()) }
        val servicePlan = servicePlan.toEntity(servicePlanToUpdate)
        return fitnessPlanRepository.save(servicePlan).toResponse()
    }

    override fun deleteServicePlan(id: String) {
        log.info("Checking deletion condition for plan with id: $id")
        val objectId = ObjectId(id)
        val plan = fitnessPlanRepository.findById(objectId)
            .orElseThrow { FitnessPlanExceptions.FitnessPlanNotFoundException(objectId.toString()) }

        val today = LocalDate.now()

        if (plan.renovation != null) {
            val daysBeforeRenovation = plan.renovation.minusDays(5)
            if (today.isBefore(daysBeforeRenovation)) {
                throw FitnessPlanExceptions.CannotDeletePlanException(
                    "El plan solo se puede cancelar dentro de los 5 días previos a la renovación (a partir del ${daysBeforeRenovation})."
                )
            }
        }

        log.info("Deleting the plan for the client")
        fitnessPlanRepository.deleteById(objectId)
    }
}