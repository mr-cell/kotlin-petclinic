package mr.cell.kotlinpetclinic.core.pets

import mr.cell.kotlinpetclinic.core.owners.Owner
import mr.cell.kotlinpetclinic.core.owners.OwnersRepository
import org.springframework.stereotype.Service

@Service
class PetsService(val pets: PetsRepository, val owners: OwnersRepository) {

    fun getPets(): Collection<Pet> {
        return pets.findAll()
    }

    fun getPetById(id: String): Pet {
        return pets.findById(id)
    }

    fun getPetsByOwnerId(id: String): Collection<Pet> {
        return pets.findByOwnerId(id)
    }

    fun addPet(createPetCommand: CreatePetCommand): Pet {
        val owner = owners.findById(createPetCommand.ownerId)
        val pet = Pet(createPetCommand.name, createPetCommand.age, owner)
        return pets.save(pet)
    }

    fun removePet(id: String): Pet {
        val pet = getPetById(id)
        pets.delete(pet)
        return pet
    }

    fun updatePet(id: String, updatePetCommand: UpdatePetCommand): Pet {
        val pet = getPetById(id)
        val owner: Owner? = updatePetCommand.ownerId?.let { owners.findById(it) }
        val updatedPet = pet.replaceValues(updatePetCommand.name, updatePetCommand.age, owner)
        pets.save(updatedPet)
        return updatedPet
    }

}