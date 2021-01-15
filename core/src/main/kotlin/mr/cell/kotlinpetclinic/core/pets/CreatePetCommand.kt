package mr.cell.kotlinpetclinic.core.pets

data class CreatePetCommand(val name: String, val age: Int, val ownerId: String)
