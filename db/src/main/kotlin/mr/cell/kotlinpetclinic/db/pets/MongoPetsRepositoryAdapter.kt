package mr.cell.kotlinpetclinic.db.pets

import mr.cell.kotlinpetclinic.core.pets.Pet
import mr.cell.kotlinpetclinic.core.pets.PetNotFoundException
import mr.cell.kotlinpetclinic.core.pets.PetsRepository
import mr.cell.kotlinpetclinic.db.owners.MongoOwnersRepositoryAdapter
import org.springframework.stereotype.Component

@Component
class MongoPetsRepositoryAdapter(
    private val pets: MongoPetsRepository,
    private val owners: MongoOwnersRepositoryAdapter
) : PetsRepository {

    override fun findAll() = pets.findAll().map { toPet(it) }

    override fun findById(id: String) = pets.findById(id)
        .map { toPet(it) }
        .orElseThrow { PetNotFoundException(id) }!!

    override fun findByOwnerId(id: String) = pets.findAllByOwnerId(id).map { toPet(it) }

    override fun delete(pet: Pet) = pets.delete(PersistedPet.fromPet(pet))

    override fun save(pet: Pet): Pet {
        val persistedPet = PersistedPet.fromPet(pet)
        return toPet(pets.save(persistedPet))
    }

    private fun toPet(persistedPet: PersistedPet): Pet {
        val ownerId = persistedPet.ownerId
        val owner = owners.findById(ownerId)

        return Pet(persistedPet.id, persistedPet.name, persistedPet.age, owner)
    }
}