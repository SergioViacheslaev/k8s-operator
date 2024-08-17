package com.study.k8s.operator.service

import com.study.k8s.operator.config.OperatorProperties
import com.study.k8s.operator.constants.LABEL_APP
import com.study.k8s.operator.exception.PodServiceException
import com.study.k8s.operator.model.PodEnvironment
import com.study.k8s.operator.model.dto.ServiceEnvironmentResponseDto
import com.study.k8s.operator.utils.getPodName
import io.fabric8.kubernetes.api.model.Pod
import io.fabric8.kubernetes.client.KubernetesClient
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class PodService(
    private val client: KubernetesClient,
    private val operatorProperties: OperatorProperties,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun getServiceEnvironment(serviceName: String): ServiceEnvironmentResponseDto {
        val podName = getPodName(serviceName, operatorProperties.namespace, operatorProperties.cluster)
        log.info("Get environment for Pod: $podName")
        val podEnvironment = getPodEnvironment(podName)
        return ServiceEnvironmentResponseDto(podEnvironment)
    }

    /**
     * Collects environment variables for each container in Pod
     */
    private fun getPodEnvironment(podName: String): List<PodEnvironment> {
        val podEnvironment = mutableListOf<PodEnvironment>()
        findPods(podName).forEach { pod ->
            val environmentVariables = pod.spec.containers
                .flatMap { it.env }
            podEnvironment.add(PodEnvironment(pod.metadata.name, environmentVariables))
        }

        return podEnvironment
    }

    private fun findPods(podName: String): List<Pod> {
        return client.pods().inNamespace(operatorProperties.namespace).withLabel(LABEL_APP, podName).list().items
            .ifEmpty { throw PodServiceException("Pod $podName not found in namespace ${operatorProperties.namespace}") }
    }

}