package com.example.kotlinjpa.web

import com.example.kotlinjpa.service.ReviewService
import com.example.kotlinjpa.service.dto.ReviewDTO
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class ReviewController constructor(
    private val reviewService: ReviewService
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("review")
    fun writeReview(@RequestBody dto: ReviewDTO) = reviewService.writeReview(dto)
}