package ch.mobi.jap.envchecker.domain.model

class DeploymentModelBuilder {

    private val deployments: MutableList<Deployment> = mutableListOf()

    fun addDeployment(environment: String, service: String, applications: List<Application>) {
        deployments.add(Deployment(environment, service, applications))
    }

    fun model(): List<Deployment> {
        return deployments
    }
}

