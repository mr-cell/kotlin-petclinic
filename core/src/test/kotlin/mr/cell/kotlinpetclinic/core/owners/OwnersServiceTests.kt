package mr.cell.kotlinpetclinic.core.owners

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.capture

@ExtendWith(MockitoExtension::class)
class OwnersServiceTests {
    companion object {
        const val ANY_ID = "id"
        val ANY_OWNER = Owner(ANY_ID, "firstName", "lastName")
    }

    @Mock
    private lateinit var ownersRepository: OwnersRepository

    @Captor
    private lateinit var ownerCaptor: ArgumentCaptor<Owner>

    private lateinit var systemUnderTest: OwnersService

    @BeforeEach
    fun beforeEach() {
        systemUnderTest = OwnersService(ownersRepository)
    }

    @Test
    fun shouldGetAllOwners() {
        `when`(ownersRepository.findAll()).thenReturn(emptyList())

        systemUnderTest.getOwners()

        verify(ownersRepository, times(1)).findAll()
    }

    @Test
    fun shouldGetOwnerById() {
        val expected = ANY_OWNER
        `when`(ownersRepository.findById(anyString())).thenReturn(ANY_OWNER)

        val actual = systemUnderTest.getOwnerById(ANY_ID)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun shouldCreateOwner() {
        val expectedOwnerFirstName = "John"
        val expectedOwnerLastName = "Doe"
        val createOwnerCommand = CreateOwnerCommand(expectedOwnerFirstName, expectedOwnerLastName)
        `when`(ownersRepository.save(capture(ownerCaptor))).then { it.getArgument<Owner>(0) }

        systemUnderTest.createOwner(createOwnerCommand)

        val actual = ownerCaptor.value
        assertThat(actual).isNotNull
        assertThat(actual.firstName).isEqualTo(expectedOwnerFirstName)
        assertThat(actual.lastName).isEqualTo(expectedOwnerLastName)
    }

    @Test
    fun shouldUpdateOwner() {
        `when`(ownersRepository.findById(anyString())).thenReturn(ANY_OWNER)
        `when`(ownersRepository.save(capture(ownerCaptor))).then { it.getArgument<Owner>(0) }
        val expectedOwnerFirstName = "Adam"
        val expectedOwnerLastName = "West"
        val updateOwnerCommand = UpdateOwnerCommand(expectedOwnerFirstName, expectedOwnerLastName)

        systemUnderTest.updateOwner(ANY_ID, updateOwnerCommand)

        val actual = ownerCaptor.value
        assertThat(actual).isNotNull
        assertThat(actual.firstName).isEqualTo(expectedOwnerFirstName)
        assertThat(actual.lastName).isEqualTo(expectedOwnerLastName)
    }

    @Test
    fun shouldDeleteOwner() {
        systemUnderTest.deleteOwner(ANY_ID)

        verify(ownersRepository, times(1)).delete(anyString())
    }
}