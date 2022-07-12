package com.example.kotlinjpa.service

import com.example.kotlinjpa.domain.Review
import com.example.kotlinjpa.domain.User
import com.example.kotlinjpa.mock.MockUtil
import com.example.kotlinjpa.repository.ReviewRepository
import com.example.kotlinjpa.repository.UserRepository
import com.example.kotlinjpa.service.dto.ReviewDTO
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.util.Optional

@ExtendWith(MockitoExtension::class)
internal class ReviewServiceTest {

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var reviewRepository: ReviewRepository

    private var reviewService: ReviewService? = null

    @BeforeEach
    fun init() {
        reviewService = ReviewServiceImpl(userRepository, reviewRepository)
    }

    @Test
    fun `리뷰 저장 하기`() {

        val jsonFile = MockUtil.readJsonFileToClass("review.json", Map::class.java) as Map

        val dto = ReviewDTO(
            jsonFile["title"] as String, jsonFile["content"] as String, 1
        )

        val userMockOptional = Optional.of(MockUtil.readJsonFileToClass("user.json", User::class.java) as User)

        val mock = Review(jsonFile["title"] as String, jsonFile["content"] as String, userMockOptional.get())

        BDDMockito.given(userRepository.findById(any())).willReturn(userMockOptional)

        BDDMockito.given(reviewRepository.save(any())).willReturn(mock)

        val entity = reviewService?.writeReview(dto)

        Assertions.assertEquals(mock.getTitle(), entity?.getTitle())
        Assertions.assertEquals(mock.getContent(), entity?.getContent())
        Assertions.assertEquals(mock.getUser(), entity?.getUser())
    }
}
