package mr.cell.kotlinpetclinic.db.pets

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MongoPetsRepository: CrudRepository<PersistedPet, String> {
    fun findAllByOwnerId(id: String): Collection<PersistedPet>
}