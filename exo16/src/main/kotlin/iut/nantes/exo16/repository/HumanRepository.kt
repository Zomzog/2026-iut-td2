package iut.nantes.exo16.repository

import org.springframework.data.jpa.repository.JpaRepository

interface HumanRepository : JpaRepository<HumanEntity, Int> {
    fun findAllByName(name: String): List<HumanEntity>
}
