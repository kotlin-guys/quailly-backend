package ru.kpfu.itis.quailly.egg.repository

interface DeletableRepository<T, ID> {
    fun deleteById(id: ID): T
}