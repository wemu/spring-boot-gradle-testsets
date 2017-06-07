package ch.mobi.jap.envchecker.config

import ch.mobi.jap.envchecker.config.dto.EnvironmentConfig
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.DefaultResourceLoader
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class EnvironmentConfigService {

    val LOG = LoggerFactory.getLogger(EnvironmentConfigService::class.java)!!

    val mapper = ObjectMapper().registerModule(KotlinModule())!!
    var environments = mutableListOf<EnvironmentConfig>()

    @Autowired
    private var resourceLoader: ResourceLoader = DefaultResourceLoader()

    @PostConstruct
    fun initialize() {
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)

        val resource = resourceLoader.getResource("classpath:config/environments.json")
        try {
            resource.inputStream.use { inputStream ->
                environments = mapper.readValue(inputStream)

            }
        } catch (e: Exception) {
            LOG.error("Could not initialize application servers.", e)
        }
    }

    fun getEnvironmentLetters(): List<String> {
        return environments.map { env -> env.environment }
    }

}
