package ru.kpfu.itis.quailly.egg.repository.api

import ru.kpfu.itis.quailly.egg.domain.model.Exchange
import ru.kpfu.itis.quailly.egg.repository.CreatableRepository
import ru.kpfu.itis.quailly.egg.repository.SearchableRepository
import ru.kpfu.itis.quailly.egg.repository.UpdatableRepository

interface ExchangeRepository :
    CreatableRepository<Exchange>,
    SearchableRepository<Exchange, Long>,
    UpdatableRepository<Exchange> {

    fun findExchangesForAccount(accountId: Long): List<Exchange>
}
