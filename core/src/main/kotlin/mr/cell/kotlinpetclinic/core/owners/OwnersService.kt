package mr.cell.kotlinpetclinic.core.owners

class OwnersService(private val ownersRepository: OwnersRepository) {

    fun getOwners(): Collection<Owner> {
        return ownersRepository.findAll()
    }

    fun getOwnerById(id: String): Owner {
        return ownersRepository.findById(id)
    }

    fun createOwner(createOwnerCommand: CreateOwnerCommand): Owner {
        return ownersRepository.save(Owner(createOwnerCommand.firstName, createOwnerCommand.lastName))
    }

    fun updateOwner(id: String, updateOwnerCommand: UpdateOwnerCommand): Owner {
        val owner = getOwnerById(id)
        val updatedOwner = owner.updateValues(updateOwnerCommand.firstName, updateOwnerCommand.lastName)
        return ownersRepository.save(updatedOwner)
    }

    fun deleteOwner(id: String) {
        ownersRepository.delete(id)
    }
}