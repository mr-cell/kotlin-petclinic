package mr.cell.kotlinpetclinic.core.visits

import mr.cell.kotlinpetclinic.core.pets.Pet

data class Visit(val id: String?, val pet: Pet, val prescriptions: Collection<String>) {

    constructor(pet: Pet, prescriptions: Collection<String>) : this(null, pet, prescriptions)

    fun updateValues(pet: Pet?, prescriptions: Collection<String>?) =
        Visit(
            this.id,
            pet ?: this.pet,
            prescriptions ?: this.prescriptions
        )
}

data class CreateVisitCommand(val petId: String, val prescriptions: Collection<String>)

data class UpdateVisitCommand(val petId: String?, val prescriptions: Collection<String>?)