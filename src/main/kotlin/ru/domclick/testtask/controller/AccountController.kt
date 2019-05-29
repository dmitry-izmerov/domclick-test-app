package ru.domclick.testtask.controller

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.domclick.testtask.service.AccountService
import java.math.BigDecimal
import javax.validation.constraints.Positive

@RequestMapping("/accounts")
@RestController
class AccountController(val accountService: AccountService) {

    @PostMapping("/{from}/transfer")
    fun transferMoney(
        @PathVariable from: Long,
        @RequestParam to: Long,
        @Validated @Positive @RequestParam money: BigDecimal
    ) {
        accountService.transferMoney(from, to, money)
    }

    @PostMapping("/{id}/add")
    fun addMoney(@PathVariable id: Long, @Validated @Positive @RequestParam money: BigDecimal) {
        accountService.addMoney(id, money)
    }

    @PostMapping("/{id}/withdraw")
    fun withdrawMoney(@PathVariable id: Long, @Validated @Positive @RequestParam money: BigDecimal) {
        accountService.withdrawMoney(id, money)
    }
}

