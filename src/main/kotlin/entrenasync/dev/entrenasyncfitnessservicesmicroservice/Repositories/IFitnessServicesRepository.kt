package entrenasync.dev.entrenasyncfitnessservicesmicroservice.Repositories

import entrenasync.dev.entrenasyncfitnessservicesmicroservice.Models.FitnessService
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface IFitnessServicesRepository: MongoRepository<FitnessService, ObjectId> {
    fun findByName(name: String): FitnessService?
}