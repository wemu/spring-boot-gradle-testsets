package ch.mobi.jap.envchecker.domain.model

interface DeploymentModel {
    fun deploymentsInEnvironment(environment: String): List<Deployment>
    fun update(builder: DeploymentModelBuilder)
}

