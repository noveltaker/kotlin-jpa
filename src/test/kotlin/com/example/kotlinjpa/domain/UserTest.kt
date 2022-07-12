package com.example.kotlinjpa.domain

import com.example.kotlinjpa.mock.MockUtil
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class UserTest {

    @Test
    fun `User 객체 생성자 테스트 케이스`() {
        val jsonFile = MockUtil.readJsonFileToClass("user.json", Map::class.java) as Map

        val email = jsonFile["email"] as String

        val password = jsonFile["password"] as String

        val entity = User(email, password)

        assertEquals(email, entity.getEmail())
        assertEquals(password, entity.getPassword())
    }
}
