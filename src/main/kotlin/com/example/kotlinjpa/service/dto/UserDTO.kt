package com.example.kotlinjpa.service.dto

import com.example.kotlinjpa.domain.User

data class UserDTO constructor(
    private var email: String, private var password: String
) {

    fun toEntity() = User(email, password)

    fun getEmail() = this.email

    fun getPassword() = this.password
}
