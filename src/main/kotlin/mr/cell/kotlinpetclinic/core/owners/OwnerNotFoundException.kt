package mr.cell.kotlinpetclinic.core.owners

import java.lang.RuntimeException

class OwnerNotFoundException(val id: String): RuntimeException("No owner found with id $id")
