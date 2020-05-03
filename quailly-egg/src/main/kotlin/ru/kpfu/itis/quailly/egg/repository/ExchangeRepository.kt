package ru.kpfu.itis.quailly.egg.repository

import ru.kpfu.itis.quailly.egg.repository.jooq.schema.tables.Exchange

interface ExchangeRepository : CrudRepository<Exchange, Long> {


}