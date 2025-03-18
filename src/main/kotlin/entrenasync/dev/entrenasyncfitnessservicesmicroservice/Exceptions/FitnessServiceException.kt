package entrenasync.dev.entrenasyncfitnessservicesmicroservice.Exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.Exception
@ResponseStatus(HttpStatus.BAD_REQUEST)
sealed class FitnessServiceException(message: String) : Exception(message){
    @ResponseStatus(HttpStatus.NOT_FOUND)
    class FitnessServiceNotFoundException(id: String) : FitnessServiceException("Fitness service not found with id $id")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    class FitnessServiceNotFoundByNameException(name: String) : FitnessServiceException("Fitness service not found with name $name")
}