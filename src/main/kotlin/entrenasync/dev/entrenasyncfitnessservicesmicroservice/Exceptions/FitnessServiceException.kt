package entrenasync.dev.entrenasyncfitnessservicesmicroservice.Exceptions

import java.lang.Exception

sealed class FitnessServiceException(message: String) : Exception(message){
    class FitnessServiceNotFoundException(id: String) : FitnessServiceException("Fitness service not found with id $id")
    class FitnessServiceNotFoundByNameException(name: String) : FitnessServiceException("Fitness service not found with name $name")
}