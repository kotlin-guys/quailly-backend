package ru.kpfu.itis.quailly.egg.web.merchandise

import org.springframework.web.bind.annotation.*
import ru.kpfu.itis.quailly.egg.domain.merchandise.MerchandiseCreationRequest
import ru.kpfu.itis.quailly.egg.domain.merchandise.MerchandiseService
import ru.kpfu.itis.quailly.egg.security.token.TokenAuthentication

@RequestMapping("/merchandises")
@RestController
class MerchandiseRest(private val merchandiseService: MerchandiseService) {

    @GetMapping
    fun merchandises(authentication: TokenAuthentication) =
        merchandiseService.findMerchandises(authentication.principal)

    @PostMapping
    fun createMerchandise(
        @RequestBody request: MerchandiseCreationRequest,
        authentication: TokenAuthentication
    ) {
        merchandiseService.createMerchandise(request, authentication.principal)
    }


}