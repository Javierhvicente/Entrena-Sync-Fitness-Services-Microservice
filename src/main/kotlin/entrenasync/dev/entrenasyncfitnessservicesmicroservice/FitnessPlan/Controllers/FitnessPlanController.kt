package entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Controllers

import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Dto.FitnessPlanCreateRequest
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Dto.FitnessPlanResponse
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Dto.FitnessPlanUpdateRequest
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Services.FitnessPlanService
import org.bson.types.ObjectId
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/services/plans")
class FitnessPlanController(private val fitnessPlanService: FitnessPlanService) {

    @RequestMapping("/{clientId}")
    fun getServicesByClientId(@PathVariable clientId: String): ResponseEntity<List<FitnessPlanResponse>> {
        val fitnessPlans = fitnessPlanService.getServicesByClientId(clientId)
        return if (fitnessPlans.isNotEmpty()) {
            ResponseEntity.ok(fitnessPlans)
        } else {
            ResponseEntity.noContent().build()
        }
    }

    @PostMapping
    fun createServicePlan(@RequestBody servicePlan: FitnessPlanCreateRequest): ResponseEntity<FitnessPlanResponse> {
        val createdPlan = fitnessPlanService.createServicePlan(servicePlan)
        return ResponseEntity.ok(createdPlan)
    }

    @PutMapping("/{id}")
    fun updateServicePlan(@PathVariable id: ObjectId, @RequestBody servicePlan: FitnessPlanUpdateRequest): ResponseEntity<FitnessPlanResponse> {
        val updatedPlan = fitnessPlanService.updateServicePlan(id, servicePlan)
        return ResponseEntity.ok(updatedPlan)
    }

    @DeleteMapping("/{id}")
    fun deleteServicePlan(@PathVariable id: ObjectId): ResponseEntity<Void> {
        fitnessPlanService.deleteServicePlan(id)
        return ResponseEntity.noContent().build()
    }
    @DeleteMapping("/hard/{id}")
    fun deleteServicePlanHard(@PathVariable id: ObjectId): ResponseEntity<Void> {
        fitnessPlanService.deleteServicePlanHard(id)
        return ResponseEntity.noContent().build()
    }
}