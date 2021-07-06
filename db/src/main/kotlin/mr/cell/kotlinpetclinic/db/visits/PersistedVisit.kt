package mr.cell.kotlinpetclinic.db.visits

import mr.cell.kotlinpetclinic.core.visits.Visit
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "visits")
data class PersistedVisit(
    @Id val id: String?,
    val petId: String,
    val ownerId: String,
    val prescriptions: Collection<String>
) {

    companion object {
        fun fromVisit(visit: Visit): PersistedVisit {
            if (visit.pet.owner.id == null) {
                throw IllegalArgumentException("Cannot assign owner with no id to a visit with id ${visit.id}")
            } else if (visit.pet.id == null) {
                throw java.lang.IllegalArgumentException("Cannot assign pet with no id to a visit with id ${visit.id}")
            } else {
                return PersistedVisit(visit.id, visit.pet.id!!, visit.pet.owner.id!!, visit.prescriptions)
            }
        }
    }

    constructor(petId: String, ownerId: String, prescriptions: Collection<String>) : this(
        null,
        petId,
        ownerId,
        prescriptions
    )
}