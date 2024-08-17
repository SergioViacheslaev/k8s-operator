package com.study.k8s.operator.model.dto

import com.study.k8s.operator.model.PodEnvironment


data class ServiceEnvironmentResponseDto(
    val pods: List<PodEnvironment>
)
