package ru.kpfu.itis.quailly.egg.repository

interface SearchableRepository<T, ID> {
    fun getById(id: ID): T?
}
