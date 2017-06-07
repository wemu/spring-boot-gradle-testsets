package ch.mobi.jap.envchecker.domain.model

import com.natpryce.hamkrest.assertion.assert
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.isEmpty
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.junit.Test

class DefaultDeploymentModelTest {

    @Test
    fun shouldReturnDeployments() {
        //arrange
        val sut: DeploymentModel = DefaultDeploymentModel()
        //act
        val result: List<Deployment> = sut.deploymentsInEnvironment("Foo")
        //assert
        assert.that(result, isEmpty)
    }

    @Test
    fun updateShouldSetModel() {
        //arange
        val sut: DeploymentModel = DefaultDeploymentModel()
        //act
        sut.update(aDeploymentModelBuilder(2, "Foo"))
        val result: List<Deployment> = sut.deploymentsInEnvironment("Foo")
        //assert
        assert.that(result.size, equalTo(2))
    }

    @Test
    fun shouldFilterOtherEnvironments() {
        //arrange
        val sut: DeploymentModel = setupDeploymentModel(aDeploymentModelBuilder(2, "Foo"))
        //act
        val result: List<Deployment> = sut.deploymentsInEnvironment("Bar")
        //assert
        assert.that(result, isEmpty)
    }

    @Test
    fun updateShouldChangeModel() {
        //arrange
        val sut: DeploymentModel = setupDeploymentModel(aDeploymentModelBuilder(2, "Foo"))
        //act
        sut.update(aDeploymentModelBuilder(5, "Bar"))
        val resultFoo: List<Deployment> = sut.deploymentsInEnvironment("Foo")
        //assert
        assert.that(resultFoo, isEmpty)
    }

    @Test
    fun updateShouldUseNewModel() {
        //arrange
        val sut: DeploymentModel = setupDeploymentModel(aDeploymentModelBuilder(2, "Foo"))
        //act
        sut.update(aDeploymentModelBuilder(5, "Bar"))
        val resultBar: List<Deployment> = sut.deploymentsInEnvironment("Bar")
        //assert
        assert.that(resultBar.size, equalTo(5))
    }

    private fun setupDeploymentModel(builder: DeploymentModelBuilder?): DeploymentModel {
        val sut = DefaultDeploymentModel()
        if(builder != null) sut.update(aDeploymentModelBuilder())
        return sut
    }

    private fun aDeploymentModelBuilder(num: Int = 0, env: String = "NoEnvironment", service: String = "NoService"): DeploymentModelBuilder {
        val deployments = mutableListOf<Deployment>()
        for (i in 1..num) {
            deployments.add(Deployment(env, service, listOf()))
        }

        // TODO use KotlinTestRunner?
        // everything is final in Kotlin, so mocking with mockito is different. for now class is open and method is open
        // Kotlin POWAAAA: https://medium.com/@dpreussler/never-say-final-mocking-kotlin-classes-in-unit-tests-314d275b82b1
        val builder = mock<DeploymentModelBuilder> {
            on { model() } doReturn deployments
        }

        return builder
    }

}

