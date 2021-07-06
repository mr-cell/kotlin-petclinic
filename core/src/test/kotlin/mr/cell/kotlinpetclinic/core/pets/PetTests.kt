package mr.cell.kotlinpetclinic.core.pets

import mr.cell.kotlinpetclinic.core.owners.Owner
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PetTests {

    companion object {
        const val PET_NAME = "Fido"
        const val PET_AGE = 3
        val PET_OWNER = Owner("John", "Doe")
    }

    @Test
    fun shouldHaveNullIdIfNotSet() {
        val pet = Pet(PET_NAME, PET_AGE, PET_OWNER)
        assertThat(pet.id).isNull()
    }

    @Test
    fun shouldUpdateNameOnly() {
        val pet = Pet(PET_NAME, PET_AGE, PET_OWNER)

        val updated = pet.updateValues("Mittens", null, null)

        with(updated) {
            assertThat(name).isEqualTo("Mittens")
            assertThat(age).isEqualTo(PET_AGE)
            assertThat(owner).isEqualTo(PET_OWNER)
        }
    }

    @Test
    fun shouldUpdateAgeOnly() {
        val pet = Pet(PET_NAME, PET_AGE, PET_OWNER)

        val updated = pet.updateValues(null, 5, null)

        with(updated) {
            assertThat(name).isEqualTo(PET_NAME)
            assertThat(age).isEqualTo(5)
            assertThat(owner).isEqualTo(PET_OWNER)
        }
    }

    @Test
    fun shouldUpdateOwnerOnly() {
        val pet = Pet(PET_NAME, PET_AGE, PET_OWNER)
        val newOwner = Owner("Adam", "West")

        val updated = pet.updateValues(null, null, newOwner)

        with(updated) {
            assertThat(name).isEqualTo(PET_NAME)
            assertThat(age).isEqualTo(PET_AGE)
            assertThat(owner).isEqualTo(newOwner)
        }
    }
}