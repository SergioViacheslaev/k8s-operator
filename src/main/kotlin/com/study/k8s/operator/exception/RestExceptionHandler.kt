package com.study.k8s.operator.exception

import com.study.k8s.operator.model.dto.ApiErrorResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class RestExceptionHandler {

    @ExceptionHandler
    fun handlePodServiceException(ex: PodServiceException): ResponseEntity<ApiErrorResponseDto> {
        val errorMessage = ApiErrorResponseDto(ex.message!!)
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }
}