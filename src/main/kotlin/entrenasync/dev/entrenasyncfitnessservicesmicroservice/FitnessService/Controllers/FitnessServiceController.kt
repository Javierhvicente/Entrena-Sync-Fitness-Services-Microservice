package entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessService.Controllers

import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessService.Dto.FitenessServiceResponse
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessService.Dto.FitnessServiceCreateRequest
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessService.Dto.FitnessServiceUpdateRequest
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessService.Services.FitnessService
import jakarta.validation.Valid
import org.bson.types.ObjectId
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/services")
class FitnessServiceController(
    private val fitenssServiceImpl: FitnessService
) {
    @GetMapping
    fun getAllServices():ResponseEntity<Page<FitenessServiceResponse>>{
        return ResponseEntity.ok().body(fitenssServiceImpl.getServices(PageRequest.of(0,10)))
    }

    @GetMapping("/{id}")
    fun getServiceById(@PathVariable id: ObjectId): ResponseEntity<FitenessServiceResponse> {
        return ResponseEntity.ok().body(fitenssServiceImpl.getServiceById(id))
    }
    @GetMapping("/name/{name}")
    fun getServiceByName(@PathVariable name: String): ResponseEntity<FitenessServiceResponse> {
        return ResponseEntity.ok().body(fitenssServiceImpl.getServiceByName(name))
    }

    @PostMapping
    fun createService(@Valid @RequestBody request: FitnessServiceCreateRequest): ResponseEntity<FitenessServiceResponse> {
        return ResponseEntity.ok().body(fitenssServiceImpl.saveService(request))
    }
    @PutMapping("/{id}")
    fun updateService(@PathVariable id: ObjectId, @Valid @RequestBody request: FitnessServiceUpdateRequest): ResponseEntity<FitenessServiceResponse> {
        return ResponseEntity.ok().body(fitenssServiceImpl.updateService(id, request))
    }

    @DeleteMapping("/{id}")
    fun deleteService(@PathVariable id: ObjectId): ResponseEntity<Void> {
        fitenssServiceImpl.deleteService(id)
        return ResponseEntity.noContent().build()
    }
}