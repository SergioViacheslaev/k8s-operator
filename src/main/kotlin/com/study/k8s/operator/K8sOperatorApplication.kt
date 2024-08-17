package com.study.k8s.operator

import com.study.k8s.operator.config.OperatorProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(OperatorProperties::class)
class K8sOperatorApplication

fun main(args: Array<String>) {
    runApplication<K8sOperatorApplication>(*args)
}
