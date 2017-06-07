package ch.mobi.jap.envchecker.config

import com.natpryce.hamkrest.Matcher
import com.natpryce.hamkrest.assertion.assert
import com.natpryce.hamkrest.present
import org.junit.Test

class EnvironmentConfigServiceTest {

    @Test
    fun testEnvironmentsAreLoaded() {
        // arrange
        val envConfService = EnvironmentConfigService()

        // act
        envConfService.initialize()

        // assert
        val isNotEmpty = Matcher(Collection<Any>::isNotEmpty)
        assert.that(envConfService.environments, isNotEmpty)
    }

    @Test
    fun testEnvironmentPropertiesAreSet() {
        // arrange
        val envConfService = EnvironmentConfigService()

        // act
        envConfService.initialize()

        // assert
        for (env in envConfService.environments) {
            val isNotBlank = Matcher(String::isNotBlank)

            assert.that(env.environment, isNotBlank)
            assert.that(env.label, isNotBlank)
            assert.that(env.pipeline, present())
        }
    }

}
