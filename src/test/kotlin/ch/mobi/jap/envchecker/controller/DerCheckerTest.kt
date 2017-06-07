package ch.mobi.jap.envchecker.controller

import ch.mobi.jap.envchecker.domain.CheckResult
import ch.mobi.jap.envchecker.domain.DerChecker
import ch.mobi.jap.envchecker.domain.model.Deployment
import ch.mobi.jap.envchecker.domain.model.DeploymentModel
import com.natpryce.hamkrest.assertion.assert
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.isEmpty
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Test

class DerCheckerTest {
    @Test
    fun shouldReturnDeploymentChecks() {
        //arrange
        val sut = DerChecker(aDeploymentModel(listOf(DeploymentResult("Foo", listOf("Walter")), DeploymentResult("Bar", listOf("Walter")))))
        //act
        val result: List<CheckResult> = sut.checkEnvironments("Foo", "Bar")
        //assert
        assert.that(result, !isEmpty)
        assert.that(result.first().from, equalTo(Deployment("Foo", "Walter", listOf())))
        assert.that(result.first().to, equalTo(Deployment("Bar", "Walter", listOf())))
    }

    @Test
    fun shouldSortResultsByService() {
        //arrange
        val sut = DerChecker(
                aDeploymentModel(
                        listOf(
                                DeploymentResult("Foo", listOf("Walter", "Muck")),
                                DeploymentResult("Bar", listOf("Muck", "Walter")))))
        //act
        val result = sut.checkEnvironments("Foo", "Bar")
        //assert
        assert.that(result.size, equalTo(2))
        for (checkResult in result) {
            assert.that(checkResult.from.service, equalTo(checkResult.to.service))
        }
    }

    @Test
    fun shouldHandleUnequalLists() {
        //arrange
        val sut = DerChecker(
                aDeploymentModel(
                        listOf(
                                DeploymentResult("Foo", listOf("Walter", "Muck", "Puck")),
                                DeploymentResult("Bar", listOf("Muck", "Walter")))))
        //act
        val result = sut.checkEnvironments("Foo", "Bar")
        //assert
        assert.that(result.size, equalTo(3))
    }

    private fun aDeploymentModel(deploymentResults: List<DeploymentResult>): DeploymentModel {
        val model = mock<DeploymentModel>()
        for (result: DeploymentResult in deploymentResults) {
            val deployments = mutableListOf<Deployment>()
            for (srv: String in result.services) {
                deployments.add(Deployment(result.env, srv, listOf()))
            }
            whenever(model.deploymentsInEnvironment(result.env)).doReturn(deployments)
        }
        return model
    }
}

data class DeploymentResult(val env: String, val services: List<String>)

