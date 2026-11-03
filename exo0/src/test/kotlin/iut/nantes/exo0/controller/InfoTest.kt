package iut.nantes.exo0.controller

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

class InfoTest{

    @Nested
    @SpringBootTest
    @AutoConfigureMockMvc
    inner class Exo7 {

        @Autowired
        lateinit var mockMvc: MockMvc

        @Test
        fun `happy path`() {
            mockMvc.get("/api/v1/info")
                .andExpect {
                    status { isOk() }
                    content { contentType("application/json") }
                    jsonPath("$.appName") { value("mon application") }
                    jsonPath("$.appVersion") { value("1.0.1") }
                    jsonPath("$.git.branch") { value("main") }
                    jsonPath("$.git.commit") { value("123456") }
                }
        }

        @Nested
        @ActiveProfiles("pony")
        inner class Exo7OtherName {

            @Autowired
            lateinit var mockMvc: MockMvc

            @Test
            fun `happy path`() {
                mockMvc.get("/api/v1/info")
                    .andExpect {
                        status { isOk() }
                        content { contentType("application/json") }
                        jsonPath("$.appName") { value("my little pony") }
                        jsonPath("$.appVersion") { value("1.33.7") }
                        jsonPath("$.git.branch") { value("main") }
                        jsonPath("$.git.commit") { value("123456") }
                    }
            }
        }
    }

    @Nested
    @ActiveProfiles("dev")
    @SpringBootTest
    @AutoConfigureMockMvc
    inner class Exo9 {

        @Autowired
        lateinit var mockMvc: MockMvc

        @Test
        fun `happy path`() {
            mockMvc.get("/api/v1/info")
                .andExpect {
                    status { isOk() }
                    content { contentType("application/json") }
                    jsonPath("$.appName") { value("mon application") }
                    jsonPath("$.appVersion") { value("1.0.2-SNAPSHOT") }
                    jsonPath("$.git.branch") { value("feature/TICKET") }
                    jsonPath("$.git.commit") { value("654321") }
                }
        }
    }

}