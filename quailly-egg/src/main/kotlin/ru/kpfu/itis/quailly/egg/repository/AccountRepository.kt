package ru.kpfu.itis.quailly.egg.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.kpfu.itis.quailly.egg.domain.model.Account

interface AccountRepository: JpaRepository<Account, Long>
