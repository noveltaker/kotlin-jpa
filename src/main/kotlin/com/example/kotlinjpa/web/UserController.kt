package com.example.kotlinjpa.web

import com.example.kotlinjpa.service.UserService
import com.example.kotlinjpa.service.dto.UserDTO
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController constructor(
    private val userService: UserService
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("signup")
    fun sigUp(@RequestBody dto: UserDTO) = userService.signUp(dto)
}