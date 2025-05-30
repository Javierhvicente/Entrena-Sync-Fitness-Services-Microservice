package entrenasync.dev.entrenasyncfitnessservicesmicroservice.Controllers


import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.Dto.FitenessServiceResponse
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.Dto.FitnessServiceCreateRequest
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.Dto.FitnessServiceUpdateRequest
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.Mappers.toEntity
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.Mappers.toResponse
import entrenasync.dev.entrenasyncfitnessservicesmicroservice.Services.IFitnessService
import io.mockk.every
import io.mockk.verify
import org.bson.types.ObjectId
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@ExtendWith(SpringExtension::class)
@WebMvcTest(FitnessServiceController::class)
class FitnessServiceControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockkBean
    private lateinit var fitnessService: IFitnessService

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
    fun getAllServices() {
        val pageable = PageRequest.of(0, 10)
        val page = PageImpl(listOf(sampleResponse), pageable, 1)
        every { fitnessService.getServices(pageable) } returns page

        mockMvc.perform(
            get("/services")
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.totalElements").value(1))
            .andExpect(jsonPath("$.content[0].name").value("sampleName"))
    }

    @Test
    fun getServiceById() {
        every { fitnessService.getServiceById(sampleId) } returns sampleResponse

        mockMvc.perform(
            get("/services/{id}", sampleId)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.name").value("sampleName"))
            .andExpect(jsonPath("$.id").value(sampleId.toString()))
    }

    @Test
    fun getServiceByName() {
        every { fitnessService.getServiceByName(sampleCreaeRequest.name) } returns sampleResponse

        mockMvc.perform(
            get("/services/name/{name}", sampleCreaeRequest.name)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.name").value("sampleName"))
            .andExpect(jsonPath("$.id").value(sampleId.toString()))
    }

    @Test
    fun createService() {
        every { fitnessService.saveService(any()) } returns sampleResponse

        mockMvc.perform(
            post("/services")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleCreaeRequest))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.name").value(sampleResponse.name))
            .andExpect(jsonPath("$.id").value(sampleId.toHexString()))
    }

    @Test
    fun updateService() {
        val updatedRequest = FitnessServiceUpdateRequest(
            name = "UpdatedName",
            price = 25.0,
            description = null,
            time = "7200",
            location = "UpdatedLocation"
        )
        val updatedEntity = updatedRequest.toEntity(sampleEntity)
        val updatedResponse = updatedEntity.toResponse().apply {
            id = sampleId.toHexString()
        }

        every { fitnessService.updateService(any(), any()) } returns updatedResponse

        mockMvc.perform(
            put("/services/{id}", sampleId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedRequest))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.name").value("UpdatedName"))
            .andExpect(jsonPath("$.id").value(sampleId.toString()))
    }

    @Test
    fun deleteService() {
        every { fitnessService.deleteService(sampleId) } returns Unit

        mockMvc.perform(
            delete("/services/{id}", sampleId)
        )
            .andExpect(status().isNoContent)

        // Verify that the service was called
        verify { fitnessService.deleteService(sampleId) }
    }
}