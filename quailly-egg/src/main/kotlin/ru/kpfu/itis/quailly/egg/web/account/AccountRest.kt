package ru.kpfu.itis.quailly.egg.web.account

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.kpfu.itis.quailly.egg.domain.account.AccountService
import ru.kpfu.itis.quailly.egg.domain.model.Account

@RequestMapping("/accounts")
@RestController
class AccountRest(
    private val accountService: AccountService
) {

    @GetMapping("/{id}")
    fun account(@PathVariable id: Long): ResponseEntity<Account> {
        val account = accountService.findAccount(id)
        return account?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.badRequest().build()
    }

    @GetMapping
    fun accounts(): List<Account> {
        return accountService.findAllAccounts()
    }

    /*@PostMapping
    fun createAccount(): Account {
        return accountService.createAccount(Account(1, "test", "228", ))
    }*/
}
