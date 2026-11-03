package iut.nantes.exo16.repository

import iut.nantes.exo16.controller.HumanDto
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
class HumanEntity(
    @Id @GeneratedValue
    var id: Int?,
    var name: String,
)

fun HumanDto.toEntity() = HumanEntity(humanId, name)

fun HumanEntity.toDto() = HumanDto(id, name)
