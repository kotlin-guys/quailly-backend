package ru.kpfu.itis.quailly.egg.repository.jooq.mapper

import org.jooq.Record
import org.jooq.Result

interface JooqRecordMapper<T, R> {
    fun map(it: Map.Entry<T, Result<Record>>): R
}