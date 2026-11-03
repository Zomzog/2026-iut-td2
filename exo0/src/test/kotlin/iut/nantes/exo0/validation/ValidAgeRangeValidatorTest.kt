package iut.nantes.exo0.validation

import iut.nantes.exo0.controller.PetSearch
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ValidAgeRangeValidatorTest {

    private val validator = ValidAgeRangeValidator()

    @ParameterizedTest(name = "minAge={0}, maxAge={1} -> {2}")
    @CsvSource(
        nullValues = ["null"],
        value = [
            // minAge, maxAge, valide
            "null, null, true",
            "null, -1, false",
            "null, 0, true",
            "null, 1, true",
            "null, 10, true",
            "-1, null, false",
            "-1, -1, false",
            "-1, 0, false",
            "-1, 1, false",
            "-1, 10, false",
            "0, null, true",
            "0, -1, false",
            "0, 0, true",
            "0, 1, true",
            "0, 10, true",
            "1, null, true",
            "1, -1, false",
            "1, 0, false",
            "1, 1, true",
            "1, 10, true",
            "10, null, true",
            "10, -1, false",
            "10, 0, false",
            "10, 1, false",
            "10, 10, true",
        ],
    )
    fun `truth table`(minAge: Int?, maxAge: Int?, expected: Boolean) {
        assertEquals(expected, validator.isValid(PetSearch(minAge, maxAge), null))
    }

    @Test
    fun `null search is valid`() {
        assertTrue(validator.isValid(null, null))
    }

    @ParameterizedTest(name = "maxRange=5, minAge={0}, maxAge={1} -> {2}")
    @CsvSource(
        nullValues = ["null"],
        value = [
            // minAge, maxAge, valide
            "0, 5, true",
            "0, 6, false",
            "10, 15, true",
            "10, 16, false",
            "null, 100, true",
            "100, null, true",
        ],
    )
    fun `max range`(minAge: Int?, maxAge: Int?, expected: Boolean) {
        val validator = ValidAgeRangeValidator().apply { initialize(ValidAgeRange(maxRange = 5)) }
        assertEquals(expected, validator.isValid(PetSearch(minAge, maxAge), null))
    }
}
