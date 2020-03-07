package ru.kpfu.itis.quailly.egg.extension

import org.springframework.data.jpa.repository.JpaRepository

fun <T, ID> JpaRepository<T, ID>.findOne(id: ID): T? = findById(id).orElse(null)