package mr.cell.kotlinpetclinic.http.visits

import mr.cell.kotlinpetclinic.core.visits.*
import mr.cell.kotlinpetclinic.http.HttpError
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/visits")
class VisitsRestController(private val visits: VisitsService) {
    companion object {
        private val LOG: Logger = LoggerFactory.getLogger(VisitsRestController::class.java)
    }

    @GetMapping
    fun findAll(): Collection<Visit> {
        return visits.getVisits()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: String): Visit {
        return visits.getVisitsById(id)
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createNewVisit(@RequestBody createVisitCommand: CreateVisitCommand): Visit {
        return visits.addVisit(createVisitCommand)
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    fun deleteVisit(@PathVariable("id") id: String) {
        try {
            visits.removeVisit(id)
        } catch (ex: VisitNotFoundException) {
            LOG.warn("Could not remove visit with id {} - not found", id)
        }
    }

    @PatchMapping("/{id}")
    fun updateVisit(@PathVariable("id") id: String, @RequestBody updateVisitCommand: UpdateVisitCommand): Visit {
        return visits.updateVisit(id, updateVisitCommand)
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(VisitNotFoundException::class)
    fun handleVisitNotFoundException(req: HttpServletRequest, ex: VisitNotFoundException): HttpError {
        LOG.error("Visit not found", ex)
        return HttpError(req.requestURI, ex.localizedMessage)
    }
}