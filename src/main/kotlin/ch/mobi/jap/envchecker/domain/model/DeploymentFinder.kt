package ch.mobi.jap.envchecker.domain.model

class DeploymentFinder(val deployments: List<Deployment>)
{
    fun findDeployments(environment: String): List<Deployment> {
        return deployments.filter { it.environment.equals(environment) }
    }
}