package mr.cell.kotlinpetclinic.core.pets

data class UpdatePetCommand(val name: String?, val age: Int?, val ownerId: String?)
