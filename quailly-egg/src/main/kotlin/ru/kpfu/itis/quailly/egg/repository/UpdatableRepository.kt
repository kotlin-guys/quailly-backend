package ru.kpfu.itis.quailly.egg.repository

interface UpdatableRepository<T> {
    fun update(entity: T): T
}