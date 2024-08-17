package com.study.k8s.operator.utils

fun getPodName(serviceName: String, namespace: String, cluster: String) = "$serviceName-$namespace-$cluster"

