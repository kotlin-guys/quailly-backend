package ru.kpfu.itis.quailly.egg.repository

interface CrudRepository<T, ID> {

    fun create(entity: T): T

    fun getById(id: ID): T

    fun getAll(): Iterable<T>

    fun update(entity: T): T

    fun delete(entity: T)
}