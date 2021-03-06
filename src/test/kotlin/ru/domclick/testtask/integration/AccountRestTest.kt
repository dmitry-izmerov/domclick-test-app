package ru.domclick.testtask.integration

import org.junit.Test
import org.springframework.boot.test.web.client.TestRestTemplate
import java.util.stream.IntStream
import kotlin.streams.toList

private const val BASE_URL = "http://localhost:8080"

class AccountRestTest {

    private val restTemplate = TestRestTemplate()

    @Test
    fun checkMultipleCallsOfTransferMoneyInParallel() {
        val threadsNum = 10
        val threads = IntStream.range(0, threadsNum).toList()
            .map {
                Thread {
                    restTemplate.postForLocation("$BASE_URL/accounts/3/transfer?to=1&money=100.00", null)
                    println("thread number: $it")
                }
            }

        threads.forEach { it.start() }
        threads.forEach { it.join() }
    }

    @Test
    fun checkMultipleCallsOfTransferMoneyConsequentially() {
        val threadsNum = 10
        for (i in 1..threadsNum) {
            val t = Thread {
                restTemplate.postForLocation("$BASE_URL/accounts/3/transfer?to=1&money=100.00", null)
                println("thread number: $i")
            }
            t.start()
            t.join()
        }
    }
}