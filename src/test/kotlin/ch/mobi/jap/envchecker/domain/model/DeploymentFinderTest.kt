package ch.mobi.jap.envchecker.domain.model

import com.natpryce.hamkrest.assertion.assert
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.isEmpty
import org.junit.Test

class DeploymentFinderTest {

    @Test
    fun shouldReturnDeploymentList() {
        //arrange
        val sut = DeploymentFinder(listOf())
        //act
        val result: List<Deployment> = sut.findDeployments("")
        //assert
        assert.that(result, isEmpty)
    }

    @Test
    fun shouldReturnNonEmptyList() {
        //arrange
        val aDeployment: Deployment = aDeploymentInEnvironment("")
        val sut = DeploymentFinder(listOf(aDeployment))
        //act
        val result: List<Deployment> = sut.findDeployments("")
        //assert
        assert.that(result, !isEmpty)
    }

    @Test
    fun shouldFilterListByEnvironment() {
        //arrange
        val deployments: List<Deployment> = listOf(aDeploymentInEnvironment("Foo"), aDeploymentInEnvironment("Bar"))
        val sut = DeploymentFinder(deployments)
        //act
        val result: List<Deployment> = sut.findDeployments("Foo")
        //assert
        assert.that(result.size, equalTo(1))
    }

    private fun aDeploymentInEnvironment(env: String = "NoEnvironment", service: String = "NoService"): Deployment {
        return Deployment(env, service, listOf())
    }
}