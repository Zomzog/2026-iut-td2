package iut.nantes.exo0.validation

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.reflect.KClass

@Target(allowedTargets = [AnnotationTarget.FIELD])
@Retention(RUNTIME)
@Constraint(validatedBy = [CapitalizedValidator::class])
annotation class Capitalized(
    val message: String = "Must start with an uppercase letter",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
)

class CapitalizedValidator : ConstraintValidator<Capitalized, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        return value.isNullOrEmpty() || value.first().isUpperCase()
    }
}
