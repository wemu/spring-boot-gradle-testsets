package ch.mobi.jap.envchecker.config.dto

import com.fasterxml.jackson.annotation.JsonProperty


enum class EnvironmentPipeline {

    @JsonProperty("agile")
    AGILE,

    @JsonProperty("conventional")
    CONVENTIONAL

}