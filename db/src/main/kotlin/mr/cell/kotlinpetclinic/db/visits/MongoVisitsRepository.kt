package mr.cell.kotlinpetclinic.db.visits

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MongoVisitsRepository: CrudRepository<PersistedVisit, String> {
    fun findByPetId(petId: String): Collection<PersistedVisit>
    fun findByOwnerId(ownerId: String): Collection<PersistedVisit>
}