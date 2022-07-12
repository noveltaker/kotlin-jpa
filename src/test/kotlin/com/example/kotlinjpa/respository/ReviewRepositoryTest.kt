package com.example.kotlinjpa.respository

import com.example.kotlinjpa.domain.Review
import com.example.kotlinjpa.domain.User
import com.example.kotlinjpa.mock.MockUtil
import com.example.kotlinjpa.repository.ReviewRepository
import com.example.kotlinjpa.repository.UserRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@DataJpaTest
@ExtendWith(SpringExtension::class)
internal class ReviewRepositoryTest {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var reviewRepository: ReviewRepository

    private var user: User? = null

    @BeforeEach
    fun init() {

        user = userRepository.save(
            MockUtil.readJsonFileToClass("user.json", User::class.java) as User
        )

        userRepository.flush()
    }

    @Test
    fun `리뷰저장 테스트 케이스`() {

        var jsonData = MockUtil.readJsonFileToClass("review.json", Map::class.java) as Map<*, *>

        var mock = Review(jsonData["title"] as String, jsonData["content"] as String, user)

        val entity = reviewRepository.save(mock)

        assertEquals(mock.getTitle(), entity.getTitle())
        assertEquals(mock.getContent(), entity.getContent())
        assertEquals(mock.getUser(), entity.getUser())
    }
}
