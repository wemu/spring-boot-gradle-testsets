package ch.mobi.jap.envchecker.controller

import org.junit.Before
import org.junit.Test
import org.springframework.mock.env.MockEnvironment
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class IndexControllerTest {

    lateinit var  mockMvc: MockMvc

    @Before
    fun setUp() {
        val mockEnv = MockEnvironment()
        this.mockMvc = MockMvcBuilders.standaloneSetup(IndexController(mockEnv, "0.0")).build()
    }

    @Test
    fun testRootPageReturns() {
        this.mockMvc
                .perform(get("/"))
                .andExpect(status().isOk)
                .andExpect(model().attributeExists("envcheckerVersion"))
                .andExpect(view().name("index"))
    }

    @Test
    fun testIndexPageContainsFooter() {
        this.mockMvc
                .perform(get("/"))
                .andExpect(status().isOk)
                .andExpect(view().name("index"))
                //.andExpect(content().string(containsString("footer")))
    }

}
