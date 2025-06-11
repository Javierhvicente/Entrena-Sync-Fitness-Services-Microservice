package entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessPlan.Exceptions

import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessService.Exceptions.FitnessServiceException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.Exception

@ResponseStatus(HttpStatus.BAD_REQUEST)
sealed class FitnessPlanExceptions(message: String) : RuntimeException(message) {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    class FitnessPlanNotFoundException(id: String) : FitnessPlanExceptions("Fitness plan was not found with client id: $id")

    @ResponseStatus(HttpStatus.CONFLICT)
    class FitnessPlanAlreadyExistsException(clientId: String, serviceId: String) : FitnessPlanExceptions("Fitness plan already exists for client id: $clientId and service id: $serviceId")

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    class CannotDeletePlanException(message: String) : FitnessPlanExceptions(message)
}