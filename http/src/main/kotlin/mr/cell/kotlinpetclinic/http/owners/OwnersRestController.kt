package mr.cell.kotlinpetclinic.http.owners

import mr.cell.kotlinpetclinic.core.owners.*
import mr.cell.kotlinpetclinic.core.pets.Pet
import mr.cell.kotlinpetclinic.core.pets.PetsService
import mr.cell.kotlinpetclinic.http.HttpError
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/owners")
class OwnersRestController(private val owners: OwnersService, private val pets: PetsService) {
    companion object {
        private val LOG: Logger = LoggerFactory.getLogger(OwnersRestController::class.java)
    }

    @GetMapping
    fun getAllOwners(): Collection<Owner> {
        return owners.getOwners()
    }

    @GetMapping("/{id}")
    fun getOwnerById(@PathVariable("id") id: String): Owner {
        return owners.getOwnerById(id)
    }

    @GetMapping("/{id}/pets")
    fun getOwnerPets(@PathVariable("id") id: String): Collection<Pet> {
        return pets.getPetsByOwnerId(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createOwner(@RequestBody owner: CreateOwnerCommand): Owner {
        return owners.createOwner(owner)
    }

    @PatchMapping("/{id}")
    fun updateOwner(@PathVariable("id") id: String, @RequestBody owner: UpdateOwnerCommand): Owner {
        return owners.updateOwner(id, owner)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteOwner(@PathVariable("id") id: String) {
        owners.deleteOwner(id)
    }

    @ExceptionHandler(OwnerNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleOwnerNotFoundException(req: HttpServletRequest, ex: OwnerNotFoundException): HttpError {
        LOG.error("Owner not found for id ${ex.id}", ex)
        return HttpError(req.requestURI, ex.localizedMessage)
    }
}