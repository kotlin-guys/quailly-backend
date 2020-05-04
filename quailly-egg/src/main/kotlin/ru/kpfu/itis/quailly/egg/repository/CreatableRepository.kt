package ru.kpfu.itis.quailly.egg.repository

interface CreatableRepository<T> {
    fun create(entity: T): T
}
