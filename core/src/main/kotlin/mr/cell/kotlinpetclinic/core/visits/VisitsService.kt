package mr.cell.kotlinpetclinic.core.visits

import mr.cell.kotlinpetclinic.core.pets.Pet
import mr.cell.kotlinpetclinic.core.pets.PetsRepository

class VisitsService(private val visits: VisitsRepository, private val pets: PetsRepository) {

    fun getVisits() = visits.findAll()

    fun getVisitsById(id: String) = visits.findById(id)

    fun getVisitsByPetId(petId: String) = visits.findByPetId(petId)

    fun getVisitsByOwnerId(ownerId: String) = visits.findByOwnerId(ownerId)

    fun addVisit(createVisitCommand: CreateVisitCommand): Visit {
        val pet = pets.findById(createVisitCommand.petId)
        val visit = Visit(pet, createVisitCommand.prescriptions)
        return visits.save(visit)
    }

    fun removeVisit(id: String): Visit {
        val visit = getVisitsById(id)
        visits.delete(visit)
        return visit
    }

    fun updateVisit(id: String, updateVisitCommand: UpdateVisitCommand): Visit {
        val visit = getVisitsById(id)
        val pet: Pet? = updateVisitCommand.petId?.let { pets.findById(it) }
        val updatedVisit = visit.updateValues(pet, updateVisitCommand.prescriptions)
        return visits.save(updatedVisit)
    }
}