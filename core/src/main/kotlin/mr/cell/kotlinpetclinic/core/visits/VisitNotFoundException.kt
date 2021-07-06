package mr.cell.kotlinpetclinic.core.visits

import java.lang.RuntimeException

class VisitNotFoundException(id: String): RuntimeException("No visit found with id $id")