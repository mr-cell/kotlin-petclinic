package mr.cell.kotlinpetclinic.db.owners

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MongoOwnersRepository : CrudRepository<PersistedOwner, String>
