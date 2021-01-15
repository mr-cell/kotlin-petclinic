package mr.cell.kotlinpetclinic.db.pets

import mr.cell.kotlinpetclinic.core.pets.Pet
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "pets")
data class PersistedPet(@Id val id: String?, val name: String, val age: Int, val ownerId: String) {

    companion object {
        fun fromPet(pet: Pet): PersistedPet {
            if (pet.owner.id == null) {
                throw IllegalArgumentException("Cannot assign owner with no id to a pet with id ${pet.id}")
            } else {
                return PersistedPet(pet.id, pet.name, pet.age, pet.owner.id!!)
            }
        }
    }

    constructor(name: String, age: Int, ownerId: String) : this(null, name, age, ownerId)
}