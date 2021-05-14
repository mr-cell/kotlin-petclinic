package mr.cell.kotlinpetclinic.core.owners

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class OwnerNotFoundExceptionTests {

    @Test
    fun shouldConstructExceptionSuccessfully() {
        val id = "dummy_id"
        val ex = OwnerNotFoundException(id)

        assertThat(ex.message).isEqualTo("No owner found with id $id")
    }
}