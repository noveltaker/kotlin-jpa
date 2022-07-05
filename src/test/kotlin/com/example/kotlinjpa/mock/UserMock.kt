package com.example.kotlinjpa.mock

import com.example.kotlinjpa.domain.User


class UserMock {

    companion object {

        private val mockEmail = "test@naver.com"

        private val mockPassword = "1234567"

        fun createdMock() = User(mockEmail, mockPassword)
    }
}