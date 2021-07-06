package mr.cell.kotlinpetclinic.db.visits

import mr.cell.kotlinpetclinic.core.visits.Visit
import mr.cell.kotlinpetclinic.core.visits.VisitNotFoundException
import mr.cell.kotlinpetclinic.core.visits.VisitsRepository
import mr.cell.kotlinpetclinic.db.pets.MongoPetsRepositoryAdapter
import org.springframework.stereotype.Component

@Component
class MongoVisitsRepositoryAdapter(
    private val visits: MongoVisitsRepository,
    private val pets: MongoPetsRepositoryAdapter
) : VisitsRepository {

    override fun findAll() = visits.findAll().map { toVisit(it) }

    override fun findById(id: String) =
        visits.findById(id).map { toVisit(it) }.orElseThrow { VisitNotFoundException(id) }!!

    override fun delete(visit: Visit) {
        visit.id?.let { visits.deleteById(it) }
    }

    override fun save(visit: Visit): Visit {
        val persistedVisit = PersistedVisit.fromVisit(visit)
        return toVisit(visits.save(persistedVisit))
    }

    override fun findByPetId(petId: String) = visits.findByPetId(petId).map { toVisit(it) }

    override fun findByOwnerId(ownerId: String) = visits.findByOwnerId(ownerId).map { toVisit(it) }

    private fun toVisit(persistedVisit: PersistedVisit): Visit {
        val pet = pets.findById(persistedVisit.petId)
        return Visit(persistedVisit.id, pet, persistedVisit.prescriptions)
    }
}