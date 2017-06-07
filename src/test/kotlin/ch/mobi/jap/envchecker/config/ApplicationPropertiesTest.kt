package ch.mobi.jap.envchecker.config

import org.hamcrest.CoreMatchers.everyItem
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.isIn
import org.junit.Test
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import java.io.IOException
import java.util.*

class ApplicationPropertiesTest {

    @Test
    @Throws(IOException::class)
    fun testApplicationPropertiesHaveSameConfigs() {
        val pathResolver = PathMatchingResourcePatternResolver()
        val resources = pathResolver.getResources("classpath*:application-*.properties")

        var referencePropNames: Set<String>? = null
        for (resource in resources) {
            println(resource.filename)
            val props = Properties()
            props.load(resource.inputStream)

            val propNames = props.stringPropertyNames()
            if (referencePropNames == null) {
                referencePropNames = propNames
            } else {
                assertThat<Set<String>>(propNames, everyItem<String>(isIn<String>(referencePropNames)))
            }
        }
    }
}