package ch.mobi.jap.envchecker.domain

import ch.mobi.jap.envchecker.domain.model.Deployment
import ch.mobi.jap.envchecker.domain.model.DeploymentModel
import org.springframework.stereotype.Service

@Service
class DerChecker(val model: DeploymentModel) {
    fun checkEnvironments(sourceName: String, targetName: String): List<CheckResult> {
        val sourceDeployments: Map<String, Deployment> = model.deploymentsInEnvironment(sourceName).associateBy({it.service}, { it })
        val targetDeployments: Map<String, Deployment> = model.deploymentsInEnvironment(targetName).associateBy({it.service}, { it })

        val allKeys: Set<String> = sourceDeployments.keys + targetDeployments.keys
        val result: MutableList<CheckResult> = mutableListOf()

        for (key: String in allKeys.sortedBy { it }) {
            val source = sourceDeployments.getOrDefault(key, defaultDeployment())
            val target = targetDeployments.getOrDefault(key, defaultDeployment())
            result.add(CheckResult(source,target))
        }

        return result.toList()
    }

    private fun defaultDeployment() = Deployment("NoEnvironment", "NoService", listOf())

}