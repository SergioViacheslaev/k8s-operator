package com.study.k8s.operator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class K8sOperatorApplication

fun main(args: Array<String>) {
    runApplication<K8sOperatorApplication>(*args)
}
