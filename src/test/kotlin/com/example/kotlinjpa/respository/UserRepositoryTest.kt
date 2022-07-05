package com.example.kotlinjpa.respository

import com.example.kotlinjpa.domain.User
import com.example.kotlinjpa.repository.UserRepository
import org.junit.jupiter.api.Assertions
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

        val email = "test@namver.com"

        val password = "123456"

        val mock = User(email, password)

        val entity = userRepository.save(mock)

        Assertions.assertEquals(mock.getId(), entity.getId())
        Assertions.assertEquals(mock.getEmail(), entity.getEmail())
        Assertions.assertEquals(mock.getPassword(), entity.getPassword())
    }
}