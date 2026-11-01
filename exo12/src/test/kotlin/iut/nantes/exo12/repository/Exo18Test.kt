package iut.nantes.exo12.repository

// Exo 18 : décommenter tout le fichier (sélectionner tout, puis Ctrl+/) une fois `PetEntity.human` créé.
// Tant que la relation n'est pas bidirectionnelle, ce fichier ne compile pas.
//
//import assertk.assertThat
//import assertk.assertions.isEqualTo
//import iut.nantes.exo12.DatabaseProxy
//import iut.nantes.exo12.controller.ContactDto
//import iut.nantes.exo12.controller.HumanDto
//import iut.nantes.exo12.controller.PetDto
//import iut.nantes.exo12.controller.PetKind
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest
//import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager
//import org.springframework.context.annotation.Import
//import kotlin.test.Test
//
//@DataJpaTest
//@Import(DatabaseProxy::class)
//class Exo18Test {
//    @Autowired
//    lateinit var databaseProxy: DatabaseProxy
//
//    @Autowired
//    lateinit var entityManager: TestEntityManager
//
//    @Test
//    fun `a pet knows its human`() {
//        val saved = databaseProxy.saveHuman(
//            HumanDto(null, "Joe", ContactDto("Joe@Doe.pom"), listOf(PetDto(null, "Java", 3, PetKind.DOG))),
//        )
//        entityManager.flush()
//        entityManager.clear()
//
//        val pet = entityManager.find(PetEntity::class.java, saved.pets[0].id!!)
//
//        assertThat(pet!!.human?.name).isEqualTo("Joe")
//    }
//}
//
