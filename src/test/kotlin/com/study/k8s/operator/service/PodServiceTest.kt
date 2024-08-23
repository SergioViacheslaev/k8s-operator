package ru.rgs.appobserver.service

import com.study.k8s.operator.config.OperatorProperties
import com.study.k8s.operator.createNewPod
import com.study.k8s.operator.exception.PodServiceException
import com.study.k8s.operator.service.PodService
import io.fabric8.kubernetes.client.KubernetesClient
import io.fabric8.kubernetes.client.server.mock.EnableKubernetesMockClient
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import ru.rgs.appobserver.constants.DEFAULT_NAMESPACE
import ru.rgs.appobserver.constants.LOGGING_LEVEL_ENV
import ru.rgs.appobserver.constants.LOGGING_LEVEL_INFO
import ru.rgs.appobserver.constants.MINIKUBE_CLUSTER
import ru.rgs.appobserver.constants.NOT_EXISTING_SERVICE_NAME
import ru.rgs.appobserver.constants.POD_NAME
import ru.rgs.appobserver.constants.SERVICE_NAME


@ExtendWith(MockitoExtension::class)
@EnableKubernetesMockClient(crud = true)
class PodServiceTest {

    @Mock
    lateinit var properties: OperatorProperties

    lateinit var client: KubernetesClient

    lateinit var podService: PodService

    @BeforeEach
    fun init() {
        podService = PodService(client, properties)
        `when`(properties.cluster).thenReturn(MINIKUBE_CLUSTER)
        `when`(properties.namespace).thenReturn(DEFAULT_NAMESPACE)
    }

    @Test
    fun `getServiceEnvironment should return environment variables`() {
        createTestPod(POD_NAME)

        val serviceEnvironment = podService.getServiceEnvironment(SERVICE_NAME)

        assertEquals(1, serviceEnvironment.pods.size)
        val envVariables = serviceEnvironment.pods[0].environmentVariables
        assertEquals(2, envVariables.size)
        assertEquals(LOGGING_LEVEL_ENV, envVariables[0].name)
        assertEquals(LOGGING_LEVEL_INFO, envVariables[0].value)
    }

    @Test
    fun `getServiceEnvironment should throw exception if no pods found`() {
        val exception = assertThrows<PodServiceException> { podService.getServiceEnvironment(NOT_EXISTING_SERVICE_NAME) }

        assertEquals("Pod non-exist-service-default-minikube not found in namespace $DEFAULT_NAMESPACE", exception.message)
    }

    private fun createTestPod(podName: String) {
        client.pods().resource(createNewPod(podName)).create()
    }

}
