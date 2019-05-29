package ru.domclick.testtask.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.domclick.testtask.entity.Account
import ru.domclick.testtask.repository.AccountRepository
import java.math.BigDecimal

private const val NOT_FOUND_MES = "Account with id: %d was not found."
private const val NEGATIVE_MES = "You cannot transfer money from account: %d, balance of account will be negative."

@Service
class AccountService(val accountRepository: AccountRepository) {

    @Transactional
    fun transferMoney(from: Long, to: Long, money: BigDecimal) {
        val fromAccount = getAccount(from)
        val toAccount = getAccount(to)

        if (fromAccount == toAccount) {
            return
        }

        val fromSubtracted = getSubtractedAmountWithCheck(fromAccount, money)
        val accounts = listOf(Account(from, fromSubtracted, fromAccount.version), Account(to, toAccount.balance.add(money), fromAccount.version))
        accountRepository.saveAll(accounts)
    }

    @Transactional
    fun addMoney(accountId: Long, money: BigDecimal) {
        val (_, balance, version) = getAccount(accountId)
        accountRepository.save(Account(accountId, balance.add(money), version))
    }

    @Transactional
    fun withdrawMoney(accountId: Long, money: BigDecimal) {
        val account = getAccount(accountId)
        val subtracted = getSubtractedAmountWithCheck(account, money)
        accountRepository.save(Account(accountId, subtracted, account.version))
    }

    private fun getAccount(id: Long) =
        accountRepository.findById(id).orElseThrow { RuntimeException(String.format(NOT_FOUND_MES, id)) }

    private fun getSubtractedAmountWithCheck(account: Account, money: BigDecimal): BigDecimal {
        val subtracted = account.balance.subtract(money)
        if (subtracted < BigDecimal.ZERO) {
            throw RuntimeException(String.format(NEGATIVE_MES, account.id))
        }
        return subtracted
    }
}
