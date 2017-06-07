package ch.mobi.jap.envchecker.domain.model

import com.natpryce.hamkrest.assertion.assert
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class DeploymentModelBuilderTest {
    @Test
    fun shouldBuildDeploymentList() {
        //arrange
        val sut: DeploymentModelBuilder = DeploymentModelBuilder()
        sut.addDeployment("Foo", "Bar", listOf())
        //act
        val result = sut.model()
        //assert
        assert.that(result.size, equalTo(1))
    }
}