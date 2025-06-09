package entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Repositories

import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Models.FitnessPlan
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface FitnessPlanRepository : MongoRepository<FitnessPlan, ObjectId> {
    fun findByClientId(clientId: String): List<FitnessPlan>
}