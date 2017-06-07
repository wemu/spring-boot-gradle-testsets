package ch.mobi.jap.envchecker.controller

import org.junit.Test

import org.junit.Before
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class HealthStatusControllerTest {

    var  mockMvc: MockMvc? = null

    @Before
    fun setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(HealthStatusController()).build()
    }

    @Test
    fun index() {
        this.mockMvc!!.perform(MockMvcRequestBuilders.get("/rest/health/status")).andExpect(MockMvcResultMatchers.status().isOk())
    }

}