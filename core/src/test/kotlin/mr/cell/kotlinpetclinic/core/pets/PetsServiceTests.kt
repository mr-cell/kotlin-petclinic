package mr.cell.kotlinpetclinic.core.pets

import mr.cell.kotlinpetclinic.core.owners.Owner
import mr.cell.kotlinpetclinic.core.owners.OwnersRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito.*
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.capture
import org.assertj.core.api.Assertions.assertThat

@ExtendWith(MockitoExtension::class)
class PetsServiceTests {

    companion object {
        const val ANY_ID = "anyId"
    }

    @Mock
    private lateinit var ownersRepository: OwnersRepository

    @Mock
    private lateinit var petsRepository: PetsRepository

    @Captor
    private lateinit var petCaptor: ArgumentCaptor<Pet>

    private lateinit var systemUnderTest: PetsService

    @BeforeEach
    fun beforeEach() {
        systemUnderTest = PetsService(petsRepository, ownersRepository)
    }

    @Test
    fun shouldGetAllPets() {
        systemUnderTest.getPets()

        verify(petsRepository, times(1)).findAll()
    }

    @Test
    fun shouldFindPetById() {
        systemUnderTest.getPetById(ANY_ID)

        verify(petsRepository, times(1)).findById(anyString())
    }

    @Test
    fun shouldCreatePet() {
        val petName = "Fido"
        val petAge = 3
        val petOwnerId = "petOwnerId"
        val petOwner = Owner(petOwnerId, "John", "Doe")
        val createPetCommand = CreatePetCommand(petName, petAge, petOwnerId)
        `when`(ownersRepository.findById(anyString())).thenReturn(petOwner)
        `when`(petsRepository.save(capture(petCaptor))).then { it.getArgument<Pet>(0) }

        systemUnderTest.addPet(createPetCommand)

        val actual = petCaptor.value
        with(actual) {
            assertThat(name).isEqualTo(petName)
            assertThat(age).isEqualTo(petAge)
            assertThat(owner).isEqualTo(petOwner)
        }
    }

    @Test
    fun shouldUpdatePet() {
        val newOwner = Owner("ownerId2", "Jane", "Eyre")
        `when`(ownersRepository.findById(anyString())).thenReturn(newOwner)
        `when`(petsRepository.findById(anyString())).then {
            Pet(it.getArgument<String>(0), "Fido", 3, Owner("ownerId1", "John", "Doe"))
        }
        `when`(petsRepository.save(capture(petCaptor))).then { it.getArgument<Pet>(0) }
        val updatePetCommand = UpdatePetCommand("Hunter", 5, "ownerId2")

        val updatedPet = systemUnderTest.updatePet(ANY_ID, updatePetCommand)
        val capturedPet = petCaptor.value
        assertThat(updatedPet).isEqualTo(capturedPet)
        with(capturedPet) {
            assertThat(name).isEqualTo("Hunter")
            assertThat(age).isEqualTo(5)
            assertThat(owner).isEqualTo(newOwner)
        }
    }

    @Test
    fun shouldDeletePet() {
        val pet = Pet(ANY_ID , "Fido", 3, Owner(ANY_ID, "John", "Doe"))
        `when`(petsRepository.findById(anyString())).thenReturn(pet)

        val removedPet = systemUnderTest.removePet(ANY_ID)

        assertThat(removedPet).isEqualTo(pet)
    }
}