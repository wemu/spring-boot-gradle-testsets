package ch.mobi.jap.envchecker

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext
import org.springframework.boot.test.web.client.TestRestTemplate
import ch.mobi.jap.envchecker.domain.LiimaClientTestDataFactory // marked as not found?? but still works???

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("development")
class EnvcheckerApplicationHttpTest {

    @Autowired
    lateinit var server: EmbeddedWebApplicationContext

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @LocalServerPort
    var port: Int = 0

    @Before
    fun setup() {
        // setup WireMock to respond to some LiimaClient requests
        // set liimaMockPort to random port
        val clientTestDataFactory = LiimaClientTestDataFactory()

        println(clientTestDataFactory)

    }

    @Test
    fun testIndexHtmlPageRenders() {
        SpringApplication.run(EnvcheckerApplication::class.java)

        // render index page
    }

}