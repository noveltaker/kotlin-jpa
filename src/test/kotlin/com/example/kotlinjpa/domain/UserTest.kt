package com.example.kotlinjpa.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class UserTest {


    @Test
    fun `User 객체 생성자 테스트 케이스`() {

        val email = "test@namver.com"

        val password = "123456"

        val entity = User(email, password)

        assertEquals(email, entity.getEmail())
        assertEquals(password, entity.getPassword())
    }
}