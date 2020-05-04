package ru.kpfu.itis.quailly.egg.repository.api

import ru.kpfu.itis.quailly.egg.domain.model.Account
import ru.kpfu.itis.quailly.egg.repository.CreatableRepository

interface AccountRepository : CreatableRepository<Account> {

    fun findByEmail(email: String): Account?

    fun findByToken(token: String): Account?

}