package mr.cell.kotlinpetclinic

import mr.cell.kotlinpetclinic.core.owners.OwnersRepository
import mr.cell.kotlinpetclinic.core.owners.OwnersService
import mr.cell.kotlinpetclinic.core.pets.PetsRepository
import mr.cell.kotlinpetclinic.core.pets.PetsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationConfiguration {

    @Bean
    fun petsService(petsRepository: PetsRepository, ownersRepository: OwnersRepository): PetsService {
        return PetsService(petsRepository, ownersRepository)
    }

    @Bean
    fun ownersService(ownersRepository: OwnersRepository): OwnersService {
        return OwnersService(ownersRepository)
    }
}