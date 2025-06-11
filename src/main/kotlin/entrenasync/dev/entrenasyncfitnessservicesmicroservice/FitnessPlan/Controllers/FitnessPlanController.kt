package entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Controllers

import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Dto.FitnessPlanCreateRequest
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Dto.FitnessPlanResponse
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Dto.FitnessPlanUpdateRequest
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Exceptions.FitnessPlanExceptions
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Services.FitnessPlanService
import jakarta.validation.Valid
import org.bson.types.ObjectId
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/services/plans")
class FitnessPlanController(private val fitnessPlanService: FitnessPlanService) {

    @RequestMapping("/{clientId}")
    fun getServicesByClientId(@PathVariable clientId: String): ResponseEntity<List<FitnessPlanResponse>> {
        return ResponseEntity.ok(fitnessPlanService.getServicesByClientId(clientId))
    }

    @PostMapping
    fun createServicePlan(@Valid @RequestBody servicePlan: FitnessPlanCreateRequest): ResponseEntity<FitnessPlanResponse> {
        val createdPlan = fitnessPlanService.createServicePlan(servicePlan)
        return ResponseEntity.ok(createdPlan)
    }

    @PutMapping("/{id}")
    fun updateServicePlan(@PathVariable id: ObjectId, @RequestBody @Valid servicePlan: FitnessPlanUpdateRequest): ResponseEntity<FitnessPlanResponse> {
        val updatedPlan = fitnessPlanService.updateServicePlan(id, servicePlan)
        return ResponseEntity.ok(updatedPlan)
    }

    @DeleteMapping("/{id}")
    fun deleteServicePlan(@PathVariable id: String): ResponseEntity<String> {
        try{
            fitnessPlanService.deleteServicePlan(id)
            return ResponseEntity.ok("Fitness plan with id: $id has been deleted successfully.")
        }catch (e: FitnessPlanExceptions) {
            return ResponseEntity.badRequest().body("Error deleting fitness plan: ${e.message}")
        }
    }
}