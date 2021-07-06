package mr.cell.kotlinpetclinic.core.owners

data class Owner(val id: String?, val firstName: String, val lastName: String) {

    constructor(firstName: String, lastName: String) : this(null, firstName, lastName)

    fun updateValues(firstName: String?, lastName: String?): Owner {
        return Owner(this.id, firstName ?: this.firstName, lastName ?: this.lastName)
    }
}

data class CreateOwnerCommand(val firstName: String, val lastName: String)

data class UpdateOwnerCommand(val firstName: String?, val lastName: String?)