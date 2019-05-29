package ru.domclick.testtask.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.domclick.testtask.entity.Account

interface AccountRepository : JpaRepository<Account, Long> {
}
