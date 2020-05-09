package ru.kpfu.itis.quailly.egg.web.merchandise.category

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import ru.kpfu.itis.quailly.egg.domain.model.MerchandiseCategory
import ru.kpfu.itis.quailly.egg.repository.api.MerchandiseCategoryRepository

@RequestMapping("/merchandise/categories")
@RestController
class MerchandiseCategoryRest(
    private val merchandiseCategoryRepository: MerchandiseCategoryRepository
) {

    @GetMapping
    fun getAll(): Flux<MerchandiseCategory> =
        Flux.fromIterable(merchandiseCategoryRepository.getAll())

}
