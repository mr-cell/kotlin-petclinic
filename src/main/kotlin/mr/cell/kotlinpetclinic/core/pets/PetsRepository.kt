package mr.cell.kotlinpetclinic.core.pets

interface PetsRepository {
    fun findAll(): Collection<Pet>
    fun findById(id: String): Pet
    fun delete(pet: Pet)
    fun save(pet: Pet): Pet
    fun findByOwnerId(id: String): Collection<Pet>
}