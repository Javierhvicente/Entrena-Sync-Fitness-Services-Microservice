package entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessService.Repositories

import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessService.Models.FitnessService
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface FitnessServicesRepository: MongoRepository<FitnessService, ObjectId> {
    fun findByName(name: String): FitnessService?
}