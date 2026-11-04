package iut.nantes.exo16

import iut.nantes.exo16.controller.HumanDto
import iut.nantes.exo16.controller.PetDto
import iut.nantes.exo16.repository.HumanRepository
import iut.nantes.exo16.repository.toDto
import iut.nantes.exo16.repository.toEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class DatabaseProxy(private val humanRepository: HumanRepository) {
    fun savePet(pet: PetDto): PetDto {
        TODO()
    }

    fun findPetById(id: Int): PetDto? {
        TODO()
    }

    fun findAllPets(): List<PetDto> {
        TODO()
    }

    fun saveHuman(humanDto: HumanDto): HumanDto =
        humanRepository.save(humanDto.toEntity()).toDto()

    fun findHumanById(id: Int): HumanDto? =
        humanRepository.findByIdOrNull(id)?.toDto()

    fun findAllHumans(name: String? = null): List<HumanDto> {
        val humans = if (name != null) humanRepository.findAllByName(name) else humanRepository.findAll()
        return humans.map { it.toDto() }
    }

    fun deleteHumansByName(name: String) {
        humanRepository.deleteAllByName(name)
    }
}
