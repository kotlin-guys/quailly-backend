package ru.kpfu.itis.quailly.egg.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.kpfu.itis.quailly.egg.domain.model.Dialog
import ru.kpfu.itis.quailly.egg.domain.model.SwipeResult

interface SwipeResultRepository : JpaRepository<SwipeResult, Long>