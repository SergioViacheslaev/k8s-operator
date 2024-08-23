package com.study.k8s.operator.constants

import ru.rgs.appobserver.constants.LOGGING_DIRECTORY_ENV
import ru.rgs.appobserver.constants.LOGGING_DIRECTORY_LOCAL
import ru.rgs.appobserver.constants.LOGGING_LEVEL_ENV
import ru.rgs.appobserver.constants.LOGGING_LEVEL_INFO
import ru.rgs.appobserver.constants.POD_NAME


const val SERVICE_ENV_RESPONSE = """
{
    "pods": [
        {
            "podName": "$POD_NAME",
            "environmentVariables": [
                {
                    "name": "$LOGGING_LEVEL_ENV",
                    "value": "$LOGGING_LEVEL_INFO"
                },
                {
                    "name": "$LOGGING_DIRECTORY_ENV",
                    "value": "$LOGGING_DIRECTORY_LOCAL"
                }
            ]
        }
    ]
}
"""