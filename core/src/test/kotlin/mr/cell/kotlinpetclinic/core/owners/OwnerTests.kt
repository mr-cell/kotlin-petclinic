package mr.cell.kotlinpetclinic.core.owners

import org.junit.jupiter.api.Test

import org.assertj.core.api.Assertions.assertThat

class OwnerTests {

    @Test
    fun shouldHaveNullIdIfNotSet() {
        val owner = Owner("firstName", "lastName")

        assertThat(owner.id).isNull()
    }

    @Test
    fun shouldReplaceOnlyFirstName() {
        val owner = Owner("id", "firstName", "lastName")

        val result = owner.replaceValues("firstName_replaced", null)

        assertThat(result.firstName).isEqualTo("firstName_replaced")
        assertThat(result.lastName).isEqualTo("lastName")
        assertThat(result.id).isEqualTo("id")
    }

    @Test
    fun shouldReplaceOnlyLastName() {
        val owner = Owner("id", "firstName", "lastName")

        val result = owner.replaceValues(null, "lastName_replaced")

        assertThat(result.firstName).isEqualTo("firstName")
        assertThat(result.lastName).isEqualTo("lastName_replaced")
        assertThat(result.id).isEqualTo("id")
    }
}