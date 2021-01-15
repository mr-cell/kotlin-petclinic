package mr.cell.kotlinpetclinic.db.owners

import mr.cell.kotlinpetclinic.core.owners.Owner
import mr.cell.kotlinpetclinic.core.owners.OwnerNotFoundException
import mr.cell.kotlinpetclinic.core.owners.OwnersRepository
import org.springframework.stereotype.Component

@Component
class MongoOwnersRepositoryAdapter(private val mongoOwnersRepository: MongoOwnersRepository) : OwnersRepository {

    override fun findAll(): Collection<Owner> {
        return mongoOwnersRepository.findAll().map { it.toOwner() }
    }

    override fun findById(id: String): Owner {
        val persistedOwner = mongoOwnersRepository.findById(id)
        if (persistedOwner.isPresent) {
            return persistedOwner.get().toOwner()
        } else {
            throw OwnerNotFoundException(id)
        }
    }

    override fun save(owner: Owner): Owner {
        return mongoOwnersRepository
            .save(PersistedOwner.fromOwner(owner))
            .toOwner()
    }

    override fun delete(id: String) {
        mongoOwnersRepository.deleteById(id)
    }
}