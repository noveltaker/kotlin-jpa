package com.example.kotlinjpa.service

import com.example.kotlinjpa.repository.ReviewRepository
import com.example.kotlinjpa.repository.UserRepository
import com.example.kotlinjpa.service.dto.ReviewDTO
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class ReviewServiceImpl(
    private val userRepository: UserRepository,
    private val reviewRepository: ReviewRepository
) : ReviewService {

    @Transactional
    override fun writeReview(dto: ReviewDTO?) = userRepository.findById(dto!!.getUserId())
        .orElseThrow { throw NullPointerException() }
        .let {
            reviewRepository.save(dto.toEntity(it))
        }
}
