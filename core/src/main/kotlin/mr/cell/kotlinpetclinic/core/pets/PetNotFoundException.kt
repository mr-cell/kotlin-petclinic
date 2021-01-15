package mr.cell.kotlinpetclinic.core.pets

import java.lang.RuntimeException

class PetNotFoundException(id: String) : RuntimeException("No pet found with id $id")
