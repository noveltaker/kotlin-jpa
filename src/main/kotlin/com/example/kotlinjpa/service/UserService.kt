package com.example.kotlinjpa.service

import com.example.kotlinjpa.domain.User
import com.example.kotlinjpa.service.dto.UserDTO

interface UserService {

    fun signUp(dto: UserDTO): User
}
