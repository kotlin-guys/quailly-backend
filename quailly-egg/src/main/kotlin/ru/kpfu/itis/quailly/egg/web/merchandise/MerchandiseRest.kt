package ru.kpfu.itis.quailly.egg.web.merchandise

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.kpfu.itis.quailly.egg.domain.merchandise.MerchandiseService

@RequestMapping("/merchandise")
@RestController
class MerchandiseRest(private val merchandiseService: MerchandiseService) {
}