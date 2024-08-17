package com.study.k8s.operator.config

import io.fabric8.kubernetes.client.KubernetesClient
import io.fabric8.kubernetes.client.KubernetesClientBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OperatorConfig {

    @Bean
    fun kubernetesClient(): KubernetesClient = KubernetesClientBuilder().build()
}