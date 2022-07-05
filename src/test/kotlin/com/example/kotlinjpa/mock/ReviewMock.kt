package com.example.kotlinjpa.mock

import com.example.kotlinjpa.domain.Review
import com.example.kotlinjpa.domain.User

class ReviewMock {

    companion object {


        private val mockTitle = "리뷰의 제목"

        private val mockContent = "리뷰의 컨텐츠"

        fun createdMock(user: User) = Review(
            mockTitle,
            mockContent,
            user
        )

    }
}