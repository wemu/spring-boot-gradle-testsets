package ch.mobi.jap.envchecker.domain.model

import org.springframework.stereotype.Component

@Component
class DefaultDeploymentModel() : DeploymentModel {

    var model: List<Deployment> = DeploymentModelBuilder().model()

    override fun deploymentsInEnvironment(environment: String): List<Deployment> {
        return model.filter { it.environment == environment }
    }

    override fun update(builder: DeploymentModelBuilder) {
        model = builder.model()
    }
}