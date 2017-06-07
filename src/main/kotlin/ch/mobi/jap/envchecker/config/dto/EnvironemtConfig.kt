package ch.mobi.jap.envchecker.config.dto

import com.fasterxml.jackson.annotation.JsonProperty

class EnvironmentConfig {

    @JsonProperty var environment: String = ""
    @JsonProperty var label: String = ""
    @JsonProperty var pipeline: EnvironmentPipeline = EnvironmentPipeline.CONVENTIONAL

}