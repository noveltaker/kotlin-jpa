package com.example.kotlinjpa.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ReviewTest {


    @Test
    fun `생성자 테스트 케이스`() {

        val mockTitle = "리뷰의 제목"

        val mockContent = "리뷰의 컨텐츠"

        val mockEmail = "test@naver.com"

        val mockPassword = "1234567"

        val user = User(mockEmail, mockPassword)

        val entity = Review(mockTitle, mockContent, user)

        assertEquals(mockTitle, entity.getTitle())
        assertEquals(mockContent, entity.getContent())
        assertEquals(user, entity.getUser())
    }
}