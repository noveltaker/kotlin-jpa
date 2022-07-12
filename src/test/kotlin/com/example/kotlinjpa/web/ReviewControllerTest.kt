package com.example.kotlinjpa.web

import com.example.kotlinjpa.domain.Review
import com.example.kotlinjpa.domain.User
import com.example.kotlinjpa.mock.MockUtil
import com.example.kotlinjpa.service.ReviewService
import com.example.kotlinjpa.service.dto.ReviewDTO
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.BDDMockito
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@ExtendWith(SpringExtension::class)
@WebMvcTest(value = [ReviewController::class])
@AutoConfigureMockMvc
internal class ReviewControllerTest {

    private var mockMvc: MockMvc? = null

    @MockBean
    private lateinit var reviewService: ReviewService

    @BeforeEach
    fun init() {
        mockMvc = MockMvcBuilders.standaloneSetup(ReviewController(reviewService)).build()
    }

    @Test
    fun `리뷰 작성 API`() {

        val user = MockUtil.readJsonFileToClass("user.json", User::class.java) as User

        val jsonData = MockUtil.readJsonFileToClass("review.json", Map::class.java) as Map

        val mock = Review(jsonData["title"] as String, jsonData["content"] as String, user)

        BDDMockito.given(reviewService.writeReview(any())).willReturn(mock)

        val objectMapper = ObjectMapper()

        val dto = ReviewDTO(
            jsonData["title"] as String, jsonData["content"] as String, 1
        )

        val content = objectMapper.writeValueAsString(dto)

        val action = mockMvc!!.perform(
            MockMvcRequestBuilders.post("/review").content(content).contentType(MediaType.APPLICATION_JSON)
        )?.andDo(MockMvcResultHandlers.print())
    }
}
