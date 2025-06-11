package entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Services

import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Dto.FitnessPlanCreateRequest
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Dto.FitnessPlanResponse
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Dto.FitnessPlanUpdateRequest
import org.bson.types.ObjectId

interface FitnessPlanService {
    fun getServicesByClientId(clientId: String): List<FitnessPlanResponse>
    fun createServicePlan(servicePlan: FitnessPlanCreateRequest): FitnessPlanResponse
    fun updateServicePlan(id: ObjectId, servicePlan: FitnessPlanUpdateRequest): FitnessPlanResponse
    fun deleteServicePlan(id: String)
}