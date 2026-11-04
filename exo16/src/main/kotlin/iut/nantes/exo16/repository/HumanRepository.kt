package iut.nantes.exo16.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional

interface HumanRepository : JpaRepository<HumanEntity, Int> {
    fun findAllByName(name: String): List<HumanEntity>

    @Modifying
    @Transactional
    @Query("DELETE FROM HumanEntity h WHERE h.name = :name")
    fun deleteAllByName(name: String)
}
