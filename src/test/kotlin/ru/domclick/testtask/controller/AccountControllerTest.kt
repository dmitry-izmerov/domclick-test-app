package ru.domclick.testtask.controller

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.transaction.annotation.Transactional

@Transactional
@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun shouldTransferMoney() {
        mockMvc.perform(
            MockMvcRequestBuilders.post("/accounts/1/transfer")
                .param("to", "2")
                .param("money", "500")
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
    }

    @Test
    fun shouldAddMoney() {
        mockMvc.perform(
            MockMvcRequestBuilders.post("/accounts/1/add")
                .param("money", "100")
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
    }

    @Test
    fun shouldWithdrawMoney() {
        mockMvc.perform(
            MockMvcRequestBuilders.post("/accounts/1/withdraw")
                .param("money", "500")
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
    }
}