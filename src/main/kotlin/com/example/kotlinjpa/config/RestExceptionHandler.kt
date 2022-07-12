package com.example.kotlinjpa.config

import com.example.kotlinjpa.config.exception.SameEmailException
import com.example.kotlinjpa.enums.MsgType
import com.example.kotlinjpa.service.dto.MsgDTO
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class RestExceptionHandler {

    // Exception Handler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun handler(e: Exception): MsgDTO = MsgDTO(MsgType.SystemError.getCode())

    // Same Email Exception Handler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SameEmailException::class)
    fun handler(e: SameEmailException): MsgDTO = MsgDTO(MsgType.SameEmailError.getCode())
}