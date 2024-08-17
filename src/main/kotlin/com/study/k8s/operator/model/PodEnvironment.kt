package com.study.k8s.operator.model

import io.fabric8.kubernetes.api.model.EnvVar

data class PodEnvironment(
    val podName: String,
    val environmentVariables: List<EnvVar>
)
