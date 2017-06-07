package ch.mobi.jap.envchecker

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
@ActiveProfiles("development")
class EnvcheckerApplicationTest {

    @Test
    fun testSpringBootApplicationStarts() {
    }

}