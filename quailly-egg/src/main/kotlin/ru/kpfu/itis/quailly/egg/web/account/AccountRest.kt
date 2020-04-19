package ru.kpfu.itis.quailly.egg.web.account

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.kpfu.itis.quailly.egg.domain.account.AccountService
import ru.kpfu.itis.quailly.egg.domain.account.AccountVerificationData
import ru.kpfu.itis.quailly.egg.domain.account.SignInStatus

@RequestMapping("/accounts")
@RestController
class AccountRest(
    private val accountService: AccountService
) {

    @PostMapping("/verify")
    fun verifyAccount(@RequestBody accountVerificationData: AccountVerificationData): ResponseEntity<*> {
        return when (val signInResult = accountService.signIn(accountVerificationData)) {
            is SignInStatus.Success -> ResponseEntity.ok(signInResult)
            is SignInStatus.Fail -> ResponseEntity.status(HttpStatus.CONFLICT).body(signInResult)
        }
    }

}

