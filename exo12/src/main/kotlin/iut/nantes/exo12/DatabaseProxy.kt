package iut.nantes.exo12

import iut.nantes.exo12.controller.HumanDto
import iut.nantes.exo12.controller.PetDto
import org.springframework.stereotype.Service

@Service
class DatabaseProxy() {
    fun savePet(pet: PetDto): PetDto {
        TODO()
    }

    fun findPetById(id: Int): PetDto? {
        TODO()
    }

    fun findAllPets(): List<PetDto> {
        TODO()
    }

    fun saveHuman(humanDto: HumanDto): HumanDto {
        TODO()
    }

    fun findHumanById(id: Int): HumanDto? {
        TODO()
    }

    fun findAllHuman(): List<HumanDto> {
        TODO()
    }
}
