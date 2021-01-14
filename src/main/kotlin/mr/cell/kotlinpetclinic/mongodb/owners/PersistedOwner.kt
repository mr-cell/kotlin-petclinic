package mr.cell.kotlinpetclinic.mongodb.owners

import mr.cell.kotlinpetclinic.core.owners.Owner
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "owners")
data class PersistedOwner(
    @Id val id: String?,
    val firstName: String,
    val lastName: String) {

    companion object {
        fun fromOwner(owner: Owner): PersistedOwner {
            return PersistedOwner(owner.id, owner.firstName, owner.lastName)
        }
    }

    constructor(firstName: String, lastName: String): this(null, firstName, lastName)

    fun toOwner(): Owner {
        return Owner(id, firstName, lastName)
    }
}
