package iut.nantes.exo16.controller

import com.jayway.jsonpath.JsonPath
import org.hamcrest.Matchers.everyItem
import org.hamcrest.Matchers.hasItem
import org.hamcrest.Matchers.hasItems
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Nested
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import kotlin.test.Test

@SpringBootTest
@AutoConfigureMockMvc
class HumanControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    /** Crée un human via l'API et retourne son identifiant. */
    private fun createHuman(name: String = "Joe"): Int {
        val body = mockMvc.post("/api/v1/humans") {
            contentType = APPLICATION_JSON
            content = humanJson(name)
        }.andExpect {
            status { isCreated() }
        }.andReturn().response.contentAsString
        return JsonPath.read<Int>(body, "$.humanId")
    }

    @Nested
    inner class Exo18 {
        @Test
        fun `create a human`() {
            mockMvc.post("/api/v1/humans") {
                contentType = APPLICATION_JSON
                content = humanJson("Joe")
            }.andExpect {
                status { isCreated() }
                content { contentType("application/json") }
                jsonPath("$.humanId") { isNumber() }
                jsonPath("$.name") { value("Joe") }
            }
        }

        @Test
        fun `read a human`() {
            val id = createHuman("Jane")

            mockMvc.get("/api/v1/humans/$id").andExpect {
                status { isOk() }
                content { contentType("application/json") }
                jsonPath("$.humanId") { value(id) }
                jsonPath("$.name") { value("Jane") }
            }
        }

        @Test
        fun `read all humans`() {
            createHuman("Averell")

            mockMvc.get("/api/v1/humans").andExpect {
                status { isOk() }
                content { contentType("application/json") }
                jsonPath("$[*].name") { value(hasItem("Averell")) }
            }
        }

        @Test
        fun `unknown human`() {
            mockMvc.get("/api/v1/humans/999999").andExpect {
                status { isNotFound() }
            }
        }
    }

    @Nested
    inner class Exo19 {
        @Test
        fun `filter by name`() {
            createHuman("Lucky")
            createHuman("Rantanplan")

            mockMvc.get("/api/v1/humans?name=Lucky").andExpect {
                status { isOk() }
                content { contentType("application/json") }
                jsonPath("$") { isNotEmpty() }
                jsonPath("$[*].name") { value(everyItem(`is`("Lucky"))) }
            }
        }

        @Test
        fun `no filter returns every human`() {
            createHuman("Lucky")
            createHuman("Rantanplan")

            mockMvc.get("/api/v1/humans").andExpect {
                status { isOk() }
                jsonPath("$[*].name") { value(hasItems("Lucky", "Rantanplan")) }
            }
        }

        @Test
        fun `unknown name returns an empty list`() {
            mockMvc.get("/api/v1/humans?name=Nobody").andExpect {
                status { isOk() }
                jsonPath("$") { isEmpty() }
            }
        }
    }

    private fun humanJson(name: String = "Joe") = """
        {
            "name": "$name"
        }
    """.trimIndent()
}
