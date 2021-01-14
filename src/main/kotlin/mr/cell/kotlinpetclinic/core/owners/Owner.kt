package mr.cell.kotlinpetclinic.core.owners

data class Owner(val id: String?, val firstName: String, val lastName: String) {

    constructor(firstName: String, lastName: String) : this(null, firstName, lastName)

    fun replaceValues(firstName: String, lastName: String): Owner {
        return Owner(this.id, firstName, lastName)
    }
}