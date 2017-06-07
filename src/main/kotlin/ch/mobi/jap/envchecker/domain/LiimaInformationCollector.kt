package ch.mobi.jap.envchecker.domain

import ch.mobi.jap.envchecker.domain.model.DeploymentModel
import ch.mobi.jap.envchecker.domain.model.DeploymentModelBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class LiimaInformationCollector(val model: DeploymentModel) {

    @Autowired
    @Value("\${liima.rest.deployments.limit}")
    var deploymentsLimit: Int = -1

    @Scheduled(fixedDelayString = "\${scheduling.pollingIntervalMs.liima}", initialDelayString = "\${scheduling.initialDelayMs.liima}")
    fun collectLiimaInformation() {
        val deploymentModelBuilder = DeploymentModelBuilder()
        // some crazy magic happening here
        model.update(deploymentModelBuilder)
    }

}
