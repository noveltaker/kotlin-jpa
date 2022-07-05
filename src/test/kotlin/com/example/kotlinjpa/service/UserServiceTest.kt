package com.example.kotlinjpa.service

import com.example.kotlinjpa.config.exception.SameEmailException
import com.example.kotlinjpa.domain.User
import com.example.kotlinjpa.repository.UserRepository
import com.example.kotlinjpa.service.dto.UserDTO
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.function.Executable
import org.mockito.ArgumentMatchers.any
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.internal.verification.VerificationModeFactory.times
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class UserServiceTest {

    @Mock
    private lateinit var userRepository: UserRepository

    private var userService: UserService? = null

    @BeforeEach
    fun `세팅`() {
        userService = UserServiceImpl(userRepository)
    }

    @Test
    fun `회원가입 로직 성공 테스트 케이스`() {

        val email = "test@namver.com"

        val password = "123456"

        val mock = User(email, password)

        val isExists = false

        BDDMockito.given(userRepository.existsByEmail(email))
            .willReturn(isExists)

        BDDMockito.given(userRepository.save(any()))
            .willReturn(mock)

        val dto = UserDTO(email, password)

        val entity = userService?.signUp(dto)

        BDDMockito.then(userRepository)
            .should(times(1))
            .existsByEmail(email)

        BDDMockito.then(userRepository)
            .should(times(1))
            .save(any())

        org.assertj.core.api.Assertions.assertThat(mock).isEqualTo(entity)
        Assertions.assertEquals(mock.getEmail(), entity?.getEmail())
        Assertions.assertEquals(mock.getPassword(), entity?.getPassword())
    }

    @Test
    fun `회원 가입 로직 테스트 케이스 SameEmailException`() {

        val email = "test@namver.com"

        val password = "123456"

        val dto = UserDTO(email, password)

        val isExists = true

        BDDMockito.given(userRepository.existsByEmail(email)).willReturn(isExists)

        Assertions.assertThrows(SameEmailException::class.java, Executable {
            userService?.signUp(dto)
        })

    }
}