package mr.cell.kotlinpetclinic.core.owners

interface OwnersRepository {
    fun findAll(): Collection<Owner>
    fun findById(id: String): Owner
    fun save(owner: Owner): Owner
    fun delete(id: String)
}