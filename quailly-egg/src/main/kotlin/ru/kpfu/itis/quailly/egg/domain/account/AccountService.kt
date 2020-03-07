package ru.kpfu.itis.quailly.egg.domain.account

import org.springframework.stereotype.Service
import ru.kpfu.itis.quailly.egg.domain.model.Account
import ru.kpfu.itis.quailly.egg.extension.findOne
import ru.kpfu.itis.quailly.egg.repository.AccountRepository

@Service
class AccountService(
    private val accountRepository: AccountRepository
) {

    fun findAccount(id: Long): Account? {
        return accountRepository.findOne(id)
    }

    fun createAccount(account: Account): Account {
        return accountRepository.save(account)
    }

    fun findAllAccounts(): List<Account> {
        return accountRepository.findAll()
    }
}
