package mr.cell.kotlinpetclinic

import mr.cell.kotlinpetclinic.core.owners.OwnersRepository
import mr.cell.kotlinpetclinic.core.owners.OwnersService
import mr.cell.kotlinpetclinic.core.pets.PetsRepository
import mr.cell.kotlinpetclinic.core.pets.PetsService
import mr.cell.kotlinpetclinic.core.visits.VisitsRepository
import mr.cell.kotlinpetclinic.core.visits.VisitsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationConfiguration {

    @Bean
    fun petsService(petsRepository: PetsRepository, ownersRepository: OwnersRepository) =
        PetsService(petsRepository, ownersRepository)

    @Bean
    fun ownersService(ownersRepository: OwnersRepository) = OwnersService(ownersRepository)

    @Bean
    fun visitsService(visitsRepository: VisitsRepository, petsRepository: PetsRepository) =
        VisitsService(visitsRepository, petsRepository)
}