package ch.mobi.jap.envchecker.domain.model

data class Deployment(val environment: String, val service: String, val applications: List<Application>)