package iut.nantes.exo0.controller

import iut.nantes.exo0.validation.Capitalized
import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank

data class PetDto(
    val id: Int?,
    @field:NotBlank
    @field:Capitalized
    val name: String,
    @field:Min(0)
    val age: Int,
    val kind: PetKind,
    @field:Valid
    val owner: OwnerDto? = null,
)

data class OwnerDto(
    @field:NotBlank
    val name: String,
    @field:Email
    val email: String,
)

enum class PetKind {
    CAT, DOG, FISH, OCTOPUS
}
