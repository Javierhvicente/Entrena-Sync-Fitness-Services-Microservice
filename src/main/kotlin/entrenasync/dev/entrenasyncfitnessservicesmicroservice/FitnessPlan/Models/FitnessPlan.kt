package entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Models

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document("FitnessPlans")
@TypeAlias("FitnessPlans")
class FitnessPlan(
    @Id
    var id: ObjectId?,

    @NotBlank(message = "Service plan price must not be empty")
    val price: Double,

    @NotBlank(message = "Client ID must not be empty")
    val clientId: String,

    @NotBlank(message = "Service ID must not be empty")
    val serviceId: String,

    @field:NotBlank(message = "Service description must not be empty")
    @field:Min(value = 10, message = "Minimum description characters must be 1")
    @field:Max(value = 250, message = "Maximum description characters must be 5")
    val description: String,

    @NotBlank(message = "Service plan name must not be empty")
    val createdAt: LocalDate,

    val renovation: LocalDate?,

    val durationInDays: Int? = null,

    var is_deleted: Boolean = false
) {


}