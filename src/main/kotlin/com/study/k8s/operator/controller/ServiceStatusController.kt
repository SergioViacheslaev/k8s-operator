package com.study.k8s.operator.controller

import com.study.k8s.operator.exception.PodServiceException
import com.study.k8s.operator.model.dto.ServiceEnvironmentResponseDto
import com.study.k8s.operator.service.PodService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class ServiceStatusController(
    private val podService: PodService
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("/env/json/{serviceName}")
    @ResponseBody
    fun getServiceEnvironmentInJson(@PathVariable serviceName: String): ResponseEntity<ServiceEnvironmentResponseDto> {
        return ResponseEntity.ok(podService.getServiceEnvironment(serviceName))
    }

    @GetMapping("/env/html/{serviceName}")
    fun getServiceEnvironmentInHTML(@PathVariable serviceName: String, model: Model): String {
        val serviceEnvironment = try {
            podService.getServiceEnvironment(serviceName)
        } catch (e: PodServiceException) {
            log.error("${e.message}")
            model.addAttribute("errorMessage", e.message)
        }
        model.addAttribute("serviceName", serviceName)
        model.addAttribute("serviceEnvironment", serviceEnvironment)
        return "service_environment"
    }

}