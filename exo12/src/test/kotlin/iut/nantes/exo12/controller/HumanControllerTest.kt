package iut.nantes.exo12.controller

import com.jayway.jsonpath.JsonPath
import iut.nantes.exo12.DatabaseProxy
import org.hamcrest.Matchers.hasItem
import org.junit.jupiter.api.Nested
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import kotlin.test.Test

@SpringBootTest
@AutoConfigureMockMvc
class HumanControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var databaseProxy: DatabaseProxy

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
    inner class Exo14 {
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
    inner class Exo15 {
        @Test
        fun `create a human with a contact`() {
            mockMvc.post("/api/v1/humans") {
                contentType = APPLICATION_JSON
                content = humanJson("Joe")
            }.andExpect {
                status { isCreated() }
                content { contentType("application/json") }
                jsonPath("$.name") { value("Joe") }
                jsonPath("$.contact.email") { value("Joe@Doe.pom") }
            }
        }

        @Test
        fun `read the contact of a human`() {
            val id = createHuman("Jane")

            mockMvc.get("/api/v1/humans/$id").andExpect {
                status { isOk() }
                jsonPath("$.name") { value("Jane") }
                jsonPath("$.contact.email") { value("Jane@Doe.pom") }
            }
        }
    }

    @Nested
    inner class Exo17 {
        @Test
        fun `create a human with a pet`() {
            mockMvc.post("/api/v1/humans") {
                contentType = APPLICATION_JSON
                content = humanJson("Joe")
            }.andExpect {
                status { isCreated() }
                content { contentType("application/json") }
                jsonPath("$.name") { value("Joe") }
                jsonPath("$.contact.email") { value("Joe@Doe.pom") }
                jsonPath("$.pets[0].name") { value("Java") }
            }
        }

        @Test
        fun `read the pets of a human`() {
            val id = createHuman("Jane")

            mockMvc.get("/api/v1/humans/$id").andExpect {
                status { isOk() }
                jsonPath("$.name") { value("Jane") }
                jsonPath("$.pets[0].name") { value("Java") }
                jsonPath("$.pets[0].id") { isNumber() }
            }
        }
    }

    @Nested
    inner class Exo19 {
        @Test
        fun `delete a pet`() {
            // Décommenter le code ci-dessous après l'ajout de `contact` et `pets` à HumanDto (exos 15 et 17)
//            val h = databaseProxy.saveHuman(HumanDto(null, "Joe", ContactDto("Joe@Doe.pom"), listOf(PetDto(null, "Java", 3, PetKind.DOG))))
//            mockMvc.delete("/api/v1/humans/${h.humanId}/pets/${h.pets[0].id}") {
//            }.andExpect {
//                status { isNoContent() }
//            }
//            mockMvc.get("/api/v1/humans/${h.humanId}") {
//            }.andExpect {
//                status { isOk() }
//                content { contentType("application/json") }
//                jsonPath("$.name") { value("Joe") }
//                jsonPath("$.contact.email") { value("Joe@Doe.pom") }
//                jsonPath("$.pets") { isEmpty() }
//            }
        }
    }

    private fun humanJson(name: String = "Joe", petName: String = "Java") = """
        {
            "name": "$name",
            "contact": {
                "email": "$name@Doe.pom"
            },
            "pets": [
                {
                    "name": "$petName",
                    "age": 3,
                    "kind": "DOG"
                }
            ]
        }
    """.trimIndent()
}
