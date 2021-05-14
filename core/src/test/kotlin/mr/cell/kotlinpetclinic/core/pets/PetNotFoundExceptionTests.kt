package mr.cell.kotlinpetclinic.core.pets

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PetNotFoundExceptionTests {

    @Test
    fun shouldConstructExceptionSuccessfully() {
        val id = "dummy_id"
        val ex = PetNotFoundException(id)

        assertThat(ex.message).isEqualTo("No pet found with id $id")
    }
}