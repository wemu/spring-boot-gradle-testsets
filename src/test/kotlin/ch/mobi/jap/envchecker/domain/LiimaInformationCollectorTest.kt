package ch.mobi.jap.envchecker.domain

import ch.mobi.jap.envchecker.domain.model.DeploymentModel
import com.nhaarman.mockito_kotlin.*
import org.junit.Before
import org.junit.Test

class LiimaInformationCollectorTest {

    lateinit var clientTestDataFactory: LiimaClientTestDataFactory
    lateinit var collector: LiimaInformationCollector
    val model = mock<DeploymentModel>()

    @Before
    fun setup() {
        clientTestDataFactory = LiimaClientTestDataFactory()

        collector = LiimaInformationCollector(model)
    }

    @Test
    fun collectLiimaInformation() {
        // arrange

        // act
        collector.collectLiimaInformation()

        // assert
        verify(model, times(1)).update(any())
    }

    @Test
    fun collectLiimaInformationWithLimit() {
        // arrange
        val limit = 2
        collector.deploymentsLimit = limit

        // act
        collector.collectLiimaInformation()

        // assert
        verify(model, times(1)).update(any())
    }
}
