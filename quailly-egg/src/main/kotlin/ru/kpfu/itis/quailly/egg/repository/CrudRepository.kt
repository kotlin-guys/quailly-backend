package ru.kpfu.itis.quailly.egg.repository

interface CrudRepository<T, ID> :
    CreatableRepository<T>,
    SearchableRepository<T, ID>,
    UpdatableRepository<T>,
    DeletableRepository<T, ID>