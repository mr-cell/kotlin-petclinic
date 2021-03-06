package mr.cell.kotlinpetclinic.core.pets

import mr.cell.kotlinpetclinic.core.owners.Owner

data class Pet(val id: String?, val name: String, val age: Int, val owner: Owner) {

    constructor(name: String, age: Int, owner: Owner) : this(null, name, age, owner)

    fun updateValues(name: String?, age: Int?, owner: Owner?): Pet {
        return Pet(this.id,
            name ?: this.name,
            age ?: this.age,
            owner ?: this.owner)
    }
}

data class CreatePetCommand(val name: String, val age: Int, val ownerId: String)

data class UpdatePetCommand(val name: String?, val age: Int?, val ownerId: String?)