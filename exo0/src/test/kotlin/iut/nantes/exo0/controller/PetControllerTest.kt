package iut.nantes.exo0.controller

import org.hamcrest.Matchers.startsWith
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put
import kotlin.test.Test

@WebMvcTest
class PetControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var petController: PetController

    @BeforeEach
    fun setup() {
        val database = petController.database // in real production code, database should be private
        database.clear()
        database[1] = PetDto(1, "Kraken", 2008, PetKind.OCTOPUS)
        database[2] = PetDto(2, "Maurice", 1, PetKind.FISH)
    }

    @Nested
    inner class Base {
        @Test
        fun `happy path`() {
            mockMvc.get("/api/v1/pets/1")
                .andExpect {
                    status { isOk() }
                    content { contentType("application/json") }
                    jsonPath("$.name") { value("Kraken") }
                }
        }
    }

    @Nested
    inner class Exo0 {
        @Test
        fun `happy path`() {
            mockMvc.post("/api/v1/pets") {
                contentType = APPLICATION_JSON
                content = """
                    {
                        "name": "Kraken",
                        "age": 2008,
                        "kind": "OCTOPUS"
                    }
                """.trimIndent()
            }
                .andExpect {
                    status { isCreated() }
                    content { contentType("application/json") }
                    jsonPath("$.name") { value("Kraken") }
                }
        }

        @Test
        fun `error case invalid age`() {
            mockMvc.post("/api/v1/pets") {
                contentType = APPLICATION_JSON
                content = """
                    {
                        "name": "Egg",
                        "age": -1,
                        "kind": "FISH"
                    }
                """.trimIndent()
            }
                .andExpect {
                    status { isBadRequest() }
                }
        }

        @Test
        fun `error case invalid name`() {
            mockMvc.post("/api/v1/pets") {
                contentType = APPLICATION_JSON
                content = """
                    {
                        "name": "",
                        "age": 1,
                        "kind": "FISH"
                    }
                """.trimIndent()
            }
                .andExpect {
                    status { isBadRequest() }
                }
        }
    }

    @Nested
    inner class Exo1 {

        @Test
        fun `invalid path id on get`() {
            mockMvc.get("/api/v1/pets/0")
                .andExpect {
                    status { isBadRequest() }
                }
        }

        @Test
        fun `happy path`() {
            mockMvc.put("/api/v1/pets/1") {
                contentType = APPLICATION_JSON
                content = """
                    {
                        "id": 1,
                        "name": "Kraken",
                        "age": 3210,
                        "kind": "OCTOPUS"
                    }
                """.trimIndent()
            }
                .andExpect {
                    status { isOk() }
                    content { contentType("application/json") }
                    jsonPath("$.name") { value("Kraken") }
                    jsonPath("$.age") { value("3210") }
                }
        }

        @Test
        fun `invalid path id`() {
            mockMvc.put("/api/v1/pets/0") {
                contentType = APPLICATION_JSON
                content = """
                    {
                        "id": 1,
                        "name": "Kraken",
                        "age": 3210,
                        "kind": "OCTOPUS"
                    }
                """.trimIndent()
            }
                .andExpect {
                    status { isBadRequest() }
                }
        }
    }

    @Nested
    inner class Exo2 {
        @Test
        fun `happy path`() {
            mockMvc.get("/api/v1/pets?minAge=2000&maxAge=2010")
                .andExpect {
                    status { isOk() }
                    content { contentType("application/json") }
                    jsonPath("$[0].name") { value("Kraken") }
                }
        }

        @Test
        fun `max only`() {
            mockMvc.get("/api/v1/pets?maxAge=2010")
                .andExpect {
                    status { isOk() }
                    content { contentType("application/json") }
                    jsonPath("$[0].name") { value("Kraken") }
                    jsonPath("$[1].name") { value("Maurice") }
                }
        }
    }

    @Nested
    inner class Exo3 {
        private fun createPet(name: String) = mockMvc.post("/api/v1/pets") {
            contentType = APPLICATION_JSON
            content = """
                {
                    "name": "$name",
                    "age": 2,
                    "kind": "OCTOPUS"
                }
            """.trimIndent()
        }

        @Test
        fun `capitalized name`() {
            createPet("Kraken")
                .andExpect {
                    status { isCreated() }
                    jsonPath("$.name") { value("Kraken") }
                }
        }

        @Test
        fun `name starting with a lowercase letter`() {
            createPet("kraken")
                .andExpect {
                    status { isBadRequest() }
                }
        }
    }

    @Nested
    inner class Exo3b {
        private fun search(body: String) = mockMvc.post("/api/v1/pets/search") {
            contentType = APPLICATION_JSON
            content = body
        }

        @Test
        fun `no bounds`() {
            search("{}")
                .andExpect {
                    status { isOk() }
                    content { contentType("application/json") }
                    jsonPath("$.length()") { value(2) }
                }
        }

        @Test
        fun `max only`() {
            search("""{ "maxAge": 2010 }""")
                .andExpect {
                    status { isOk() }
                    jsonPath("$.length()") { value(2) }
                }
        }

        @Test
        fun `min equals max`() {
            search("""{ "minAge": 1, "maxAge": 1 }""")
                .andExpect {
                    status { isOk() }
                    content { contentType("application/json") }
                    jsonPath("$[0].name") { value("Maurice") }
                }
        }

        @Test
        fun `min greater than max`() {
            search("""{ "minAge": 2011, "maxAge": 2010 }""")
                .andExpect {
                    status { isBadRequest() }
                }
        }

        @Test
        fun `negative min`() {
            search("""{ "minAge": -1 }""")
                .andExpect {
                    status { isBadRequest() }
                }
        }

        @Test
        fun `negative max`() {
            search("""{ "maxAge": -1 }""")
                .andExpect {
                    status { isBadRequest() }
                }
        }
    }


    @Nested
    inner class Exo4 {

        @Test
        fun `happy path`() {
            mockMvc.put("/api/v1/pets/8") {
                contentType = APPLICATION_JSON
                content = """
                    {
                        "id": 8,
                        "name": "Kraken",
                        "age": 3210,
                        "kind": "OCTOPUS"
                    }
                """.trimIndent()
            }
                .andExpect {
                    status { isIAmATeapot() }
                }
        }
    }

    @Nested
    inner class Exo5 {
        @Test
        fun `error case invalid age`() {
            mockMvc.post("/api/v1/pets") {
                contentType = APPLICATION_JSON
                content = """
                    {
                        "name": "Pinkie Pie",
                        "age": 100,
                        "kind": "PONY"
                    }
                """.trimIndent()
            }.andDo { print() }
                .andExpect {
                    status { isBadRequest() }
                    content { contentType("application/json") }
                    jsonPath("$.status") { value("400") }
                    jsonPath("$.message") { startsWith("JSON parse error") }
                    jsonPath("$.title") { doesNotExist() }
                }
        }
    }
}