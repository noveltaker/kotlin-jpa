package com.example.kotlinjpa.service.dto

import com.example.kotlinjpa.domain.Review
import com.example.kotlinjpa.domain.User


data class ReviewDTO constructor(
    private val title: String,
    private val content: String,
    private val userId: Long
) {

    fun toEntity(user: User) = Review(title, content, user)

    fun getUserId() = this.userId
}