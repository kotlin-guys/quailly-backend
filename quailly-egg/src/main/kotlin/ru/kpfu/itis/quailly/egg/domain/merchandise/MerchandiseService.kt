package ru.kpfu.itis.quailly.egg.domain.merchandise

import org.springframework.stereotype.Service
import ru.kpfu.itis.quailly.egg.repository.MerchandiseRepository

@Service
class MerchandiseService(private val merchandiseRepository: MerchandiseRepository) {

    /* fun createMerchandise(merchandiseCreationRequest: MerchandiseCreationRequest) {
         val newMerchandise = Merchandise(
             name = merchandiseCreationRequest.name,
             description = merchandiseCreationRequest.description,
             author =
         )
         merchandiseRepository.save(newMerchandise)
     }*/
}
