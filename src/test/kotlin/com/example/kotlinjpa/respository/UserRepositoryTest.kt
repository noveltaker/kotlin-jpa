package com.example.kotlinjpa.respository

import com.example.kotlinjpa.domain.User
import com.example.kotlinjpa.mock.MockUtil
import com.example.kotlinjpa.repository.UserRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@DataJpaTest
@ExtendWith(SpringExtension::class)
internal class UserRepositoryTest {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Test
    fun `단일 유저 저장`() {

        val mock = MockUtil.readJsonFileToClass("user.json", User::class.java) as User

        val entity = userRepository.save(mock)

        Assertions.assertEquals(mock.getId(), entity.getId())
        Assertions.assertEquals(mock.getEmail(), entity.getEmail())
        Assertions.assertEquals(mock.getPassword(), entity.getPassword())
    }

    @Nested
    inner class Exists {

        private var mock: User? = null

        @BeforeEach
        fun init() {
            val user = MockUtil.readJsonFileToClass("user.json", User::class.java) as User
            mock = userRepository.save(user)
            userRepository.flush()
        }

        @Test
        fun `유저 이메일 별로 존재 유무 체크 로직 존재 하는 케이스`() {
            val isExists = userRepository.existsByEmail(mock?.getEmail()!!)
            Assertions.assertTrue(isExists)
        }

        @Test
        fun `유저 이메일 별로 존재 유무 체크 로직 존재하지 않는 케이스`() {
            val mockEmail = "test1@naver.com"
            val isExists = userRepository.existsByEmail(mockEmail)
            Assertions.assertFalse(isExists)
        }

    }

}