package ru.kpfu.itis.quailly.egg.repository

import ru.kpfu.itis.quailly.egg.domain.model.Account

interface AccountRepository : CrudRepository<Account, Long> {

    fun findByEmail(email: String): Account?

    fun findByToken(token: String): Account?

}