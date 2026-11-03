package iut.nantes.exo16

import iut.nantes.exo16.controller.HumanDto
import iut.nantes.exo16.controller.PetDto
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

    fun findAllHumans(): List<HumanDto> {
        TODO()
    }
}
