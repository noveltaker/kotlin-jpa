package com.example.kotlinjpa.service

import com.example.kotlinjpa.domain.Review
import com.example.kotlinjpa.service.dto.ReviewDTO

interface ReviewService {

    fun writeReview(dto: ReviewDTO?): Review
}
