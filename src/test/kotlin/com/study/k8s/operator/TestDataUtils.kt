package com.study.k8s.operator

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.study.k8s.operator.constants.SERVICE_ENV_RESPONSE
import com.study.k8s.operator.model.dto.ServiceEnvironmentResponseDto
import io.fabric8.kubernetes.api.model.Container
import io.fabric8.kubernetes.api.model.ContainerPort
import io.fabric8.kubernetes.api.model.EnvVar
import io.fabric8.kubernetes.api.model.Pod
import io.fabric8.kubernetes.api.model.PodBuilder
import ru.rgs.appobserver.constants.APP_LABEL
import ru.rgs.appobserver.constants.CONTAINER_PORT
import ru.rgs.appobserver.constants.DEFAULT_NAMESPACE
import ru.rgs.appobserver.constants.LOGGING_DIRECTORY_ENV
import ru.rgs.appobserver.constants.LOGGING_DIRECTORY_LOCAL
import ru.rgs.appobserver.constants.LOGGING_LEVEL_ENV
import ru.rgs.appobserver.constants.LOGGING_LEVEL_INFO
import ru.rgs.appobserver.constants.SERVICE_NAME
import java.util.Collections.singletonMap

val mapper = jacksonObjectMapper()

fun createNewPod(podName: String): Pod {
    val envVariables = listOf(
        EnvVar().toBuilder()
            .withName(LOGGING_LEVEL_ENV)
            .withValue(LOGGING_LEVEL_INFO)
            .build(),
        EnvVar().toBuilder()
            .withName(LOGGING_DIRECTORY_ENV)
            .withValue(LOGGING_DIRECTORY_LOCAL)
            .build()
    )

    return PodBuilder()
        .withNewMetadata()
        .withNamespace(DEFAULT_NAMESPACE)
        .withName(SERVICE_NAME)
        .addToLabels(singletonMap(APP_LABEL, podName))
        .endMetadata()
        .withNewSpec()
        .withContainers(
            Container().toBuilder()
                .withPorts(ContainerPort().toBuilder().withContainerPort(CONTAINER_PORT).build())
                .withEnv(envVariables).build()
        )
        .endSpec()
        .build()
}

fun createServiceEnvironmentResponseDto(): ServiceEnvironmentResponseDto =
    mapper.readValue(SERVICE_ENV_RESPONSE, ServiceEnvironmentResponseDto::class.java)
