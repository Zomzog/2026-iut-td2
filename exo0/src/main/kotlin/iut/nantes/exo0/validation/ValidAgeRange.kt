package iut.nantes.exo0.validation

import iut.nantes.exo0.controller.PetSearch
import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.reflect.KClass

@Target(allowedTargets = [AnnotationTarget.CLASS])
@Retention(RUNTIME)
@Constraint(validatedBy = [ValidAgeRangeValidator::class])
annotation class ValidAgeRange(
    val maxRange: Int = Int.MAX_VALUE,
    val message: String = "Invalid age range",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
)

class ValidAgeRangeValidator : ConstraintValidator<ValidAgeRange, PetSearch> {
    private var maxRange: Int = Int.MAX_VALUE

    override fun initialize(constraintAnnotation: ValidAgeRange) {
        maxRange = constraintAnnotation.maxRange
    }

    override fun isValid(value: PetSearch?, context: ConstraintValidatorContext?): Boolean {
        if (value == null) return true
        val minAge = value.minAge
        val maxAge = value.maxAge
        if (minAge != null && minAge < 0) return false
        if (maxAge != null && maxAge < 0) return false
        if (minAge != null && maxAge != null && minAge > maxAge) return false
        if (minAge != null && maxAge != null && maxAge - minAge > maxRange) return false
        return true
    }
}
