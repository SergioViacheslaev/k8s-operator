package com.study.k8s.operator.controller

import com.study.k8s.operator.model.dto.ServiceEnvironmentResponseDto
import com.study.k8s.operator.service.PodService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class ServiceStatusController(
    private val podService: PodService
) {

    @GetMapping("/env/json/{serviceName}")
    @ResponseBody
    fun getServiceEnvironmentInJson(@PathVariable serviceName: String): ResponseEntity<ServiceEnvironmentResponseDto> {
        return ResponseEntity.ok(podService.getServiceEnvironment(serviceName))
    }

}