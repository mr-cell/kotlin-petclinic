package mr.cell.kotlinpetclinic.http.pets

import mr.cell.kotlinpetclinic.core.pets.*
import mr.cell.kotlinpetclinic.http.HttpError
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/pets")
class PetsRestController(private val pets: PetsService) {
    companion object {
        private val LOG: Logger = LoggerFactory.getLogger(PetsRestController::class.java)
    }

    @GetMapping
    fun getAllPets(): Collection<Pet> {
        return pets.getPets()
    }

    @GetMapping("/{id}")
    fun getPetById(@PathVariable("id") id: String): Pet {
        return pets.getPetById(id)
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createNewPet(@RequestBody createPetCommand: CreatePetCommand): Pet {
        return pets.addPet(createPetCommand)
    }

    @PatchMapping("/{id}")
    fun updatePet(@PathVariable id: String, @RequestBody addPetCommand: UpdatePetCommand): Pet {
        return pets.updatePet(id, addPetCommand)
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    fun deletePet(@PathVariable id: String) {
        try {
            pets.removePet(id)
        } catch (ex: PetNotFoundException) {
            LOG.warn("Could not remove pet with id {} - not found", id)
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PetNotFoundException::class)
    fun handlePetNotFoundException(req: HttpServletRequest, ex: PetNotFoundException): HttpError {
        LOG.error("Pet Not Found", ex)
        return HttpError(req.requestURI, ex.localizedMessage)
    }
}