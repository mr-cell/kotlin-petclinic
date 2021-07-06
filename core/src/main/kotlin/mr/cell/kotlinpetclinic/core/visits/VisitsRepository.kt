package mr.cell.kotlinpetclinic.core.visits

interface VisitsRepository {
    fun findAll(): Collection<Visit>
    fun findById(id: String): Visit
    fun delete(visit: Visit)
    fun save(visit: Visit): Visit
    fun findByPetId(petId: String): Collection<Visit>
    fun findByOwnerId(ownerId: String): Collection<Visit>
}