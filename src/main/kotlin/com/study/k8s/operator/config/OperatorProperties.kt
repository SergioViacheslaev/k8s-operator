package com.study.k8s.operator.config

import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.BeanCreationException
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("operator")
data class OperatorProperties(
        val cluster: String,
        val namespace: String
) {
    @PostConstruct
    fun validateProperties() {
        when {
            cluster.isBlank() -> throw BeanCreationException("Cluster is not defined")
            namespace.isBlank() -> throw BeanCreationException("Namespace is not defined")
        }
    }
}
