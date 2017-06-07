package ch.mobi.jap.envchecker.domain

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule

class LiimaClientTestDataFactory {

    val mapper = ObjectMapper().registerModule(KotlinModule())!!
    init {
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        mapper.disable(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE)
    }
}
