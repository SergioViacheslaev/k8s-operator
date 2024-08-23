package ru.rgs.appobserver.constants

import org.springframework.http.MediaType

//EnvVar
const val LOGGING_LEVEL_ENV = "LOGGING_LEVEL"
const val LOGGING_LEVEL_INFO = "Info"
const val LOGGING_DIRECTORY_ENV = "LOGGING_DIRECTORY"
const val LOGGING_DIRECTORY_LOCAL = "/local"

//Pod
const val APP_LABEL = "app"
const val DEFAULT_NAMESPACE = "default"
const val MINIKUBE_CLUSTER = "minikube"
const val NOT_EXISTING_SERVICE_NAME = "non-exist-service"
const val SERVICE_NAME = "test-service"
const val CONTAINER_PORT = 8080
const val POD_NAME = "$SERVICE_NAME-$DEFAULT_NAMESPACE-$MINIKUBE_CLUSTER"

//Model
const val SERVICE_ENVIRONMENT_VIEW = "service_environment"
const val SERVICE_NAME_ATTRIBUTE = "serviceName"
const val SERVICE_ENV_ATTRIBUTE = "serviceEnvironment"
const val ERROR_MESSAGE_ATTRIBUTE = "errorMessage"

//Response
const val HTML_UTF8 = "${MediaType.TEXT_HTML_VALUE};charset=UTF-8"
const val ENV_VAR_COLUMN = "Переменные окружения"
const val ENV_VAR_VALUE_COLUMN = "Значения"
const val POD_NOT_FOUND = "$POD_NAME not found in namespace $DEFAULT_NAMESPACE"