package entrenasync.dev.entrenasyncfitnessservicesmicroservice.Services

import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessService.Dto.FitnessServiceCreateRequest
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessService.Dto.FitnessServiceUpdateRequest
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessService.Exceptions.FitnessServiceException
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessService.Services.FitenssServiceImpl
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.bson.types.ObjectId
import org.junit.jupiter.api.Test
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessService.Mappers.toEntity
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessService.Mappers.toResponse
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.FitnessService.Repositories.FitnessServicesRepository
import io.mockk.every
import io.mockk.verify

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest

@ExtendWith(MockKExtension::class)
class FitenssServiceImplTest {

    @MockK
    lateinit var fitnessRepository: FitnessServicesRepository

    @InjectMockKs
    lateinit var fitnessServiceImpl: FitenssServiceImpl

    private val sampleId = ObjectId.get()
    private val sampleCreaeRequest = FitnessServiceCreateRequest(
        name =  "sampleName",
        price = 20.0,
        description = "SampleDescription",
        time = "3600",
        location = "SampleLocation"
    )

    private val sampleEntity by lazy {
        sampleCreaeRequest.toEntity().apply {
            id = sampleId
        }
    }

    private val sampleResponse by lazy { sampleEntity.toResponse() }

    @Test
    fun getServices() {
        val pageable = PageRequest.of(0, 5)
        val page = PageImpl(listOf(sampleEntity), pageable, 1)
        every { fitnessRepository.findAll(pageable) } returns page

        val result = fitnessServiceImpl.getServices(pageable)

        assertEquals(1, result.totalElements)
        assertEquals("sampleName", result.content.first().name)
        verify { fitnessRepository.findAll(pageable) }
    }

    @Test
    fun getServiceById() {
        every { fitnessRepository.findById(sampleId) } returns java.util.Optional.of(sampleEntity)

        val result = fitnessServiceImpl.getServiceById(sampleId)

        assertNotNull(result)

        assertEquals(sampleResponse.name, result.name)

        verify { fitnessRepository.findById(sampleId) }
    }

    @Test
    fun `getserviceById throws FitnessServiceNotFoundException when not found`() {
        every { fitnessRepository.findById(sampleId) }
            .returns(java.util.Optional.empty())
        assertThrows(FitnessServiceException.FitnessServiceNotFoundException::class.java) {
            fitnessServiceImpl.getServiceById(sampleId)
        }
        verify { fitnessRepository.findById(sampleId) }
    }

    @Test
    fun getServiceByName() {
        every { fitnessRepository.findByName(sampleCreaeRequest.name) } returns sampleEntity

        val result = fitnessServiceImpl.getServiceByName(sampleCreaeRequest.name)

        assertNotNull(result)
        assertEquals(sampleResponse.name, result.name)

        verify { fitnessRepository.findByName(sampleCreaeRequest.name) }
    }

    @Test
    fun `getServiceByName throws FitnessServiceNotFoundException when not found`() {
        every { fitnessRepository.findByName(sampleCreaeRequest.name) }
            .returns(null)

        assertThrows(FitnessServiceException.FitnessServiceNotFoundException::class.java) {
            fitnessServiceImpl.getServiceByName(sampleCreaeRequest.name)
        }

        verify { fitnessRepository.findByName(sampleCreaeRequest.name) }
    }

    @Test
    fun saveService() {
        every { fitnessRepository.save(any()) } returns sampleEntity

        val result = fitnessServiceImpl.saveService(sampleCreaeRequest)

        assertNotNull(result)
        assertEquals(sampleResponse.name, result.name)

        verify { fitnessRepository.save(any()) }
    }

    @Test
    fun updateService() {
        val updateRequest = FitnessServiceUpdateRequest(
            name = "UpdatedName",
            price = 25.0,
            description = null,
            time = "7200",
            location = "UpdatedLocation"
        )
        val updatedEntity = updateRequest.toEntity(sampleEntity)

        every { fitnessRepository.findById(sampleId) }.returns(java.util.Optional.of(sampleEntity))

        every { fitnessRepository.save(any()) } returns updateRequest.toEntity(sampleEntity).apply {
            id = sampleId
            updatedAt = sampleEntity.updatedAt
        }

        val result = fitnessServiceImpl.updateService(sampleId, updateRequest)

        assertNotNull(result)
        assertEquals(updateRequest.name, result.name)
        assertEquals(updateRequest.price, result.price)
        assertEquals(updateRequest.time, result.time)
        assertEquals(updateRequest.location, result.location)
        assertEquals(sampleEntity.description, result.description)
        assertEquals(sampleId.toHexString(), result.id)

         // Verificar que se llamaron los métodos correctos
        verify {
            fitnessRepository.findById(sampleId)
            fitnessRepository.save(any())
        }
    }

    @Test
    fun `updateService throws FitnessServiceNotFoundException when not found`() {
        val updateRequest = FitnessServiceUpdateRequest(
            name = "UpdatedName",
            price = 25.0,
            description = null,
            time = "7200",
            location = "UpdatedLocation"
        )
        every { fitnessRepository.findById(sampleId) }
            .returns(java.util.Optional.empty())

        assertThrows(FitnessServiceException.FitnessServiceNotFoundException::class.java) {
            fitnessServiceImpl.updateService(sampleId, updateRequest)
        }

        verify { fitnessRepository.findById(sampleId) }
    }

    @Test
    fun deleteService() {
        every { fitnessRepository.deleteById(sampleId) } returns Unit

        fitnessServiceImpl.deleteService(sampleId)

        verify {
            fitnessRepository.deleteById(any())
        }
    }

    @Test
    fun `deleteService throws FitnessServiceNotFoundException when not found`() {
        every { fitnessRepository.deleteById(sampleId) }
            .throws(FitnessServiceException.FitnessServiceNotFoundException(sampleId.toHexString()))

        assertThrows(FitnessServiceException.FitnessServiceNotFoundException::class.java) {
            fitnessServiceImpl.deleteService(sampleId)
        }

        verify { fitnessRepository.deleteById(sampleId) }
    }
}