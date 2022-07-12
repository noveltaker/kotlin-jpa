package com.example.kotlinjpa.domain

import com.example.kotlinjpa.mock.MockUtil
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ReviewTest {

    @Test
    fun `생성자 테스트 케이스`() {

        val user = MockUtil.readJsonFileToClass("user.json", User::class.java) as User

        val jsonData = MockUtil.readJsonFileToClass("review.json", Map::class.java) as Map

        val entity = Review(jsonData["title"] as String, jsonData["content"] as String, user)

        assertEquals(jsonData["title"] as String, entity.getTitle())
        assertEquals(jsonData["content"] as String, entity.getContent())
        assertEquals(user, entity.getUser())
    }
}
