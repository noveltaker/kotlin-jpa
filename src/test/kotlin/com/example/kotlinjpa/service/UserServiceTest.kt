package com.example.kotlinjpa.service

import com.example.kotlinjpa.domain.User
import com.example.kotlinjpa.repository.UserRepository
import com.example.kotlinjpa.service.dto.UserDTO
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
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
    fun `회원가입 로직`() {

        val email = "test@namver.com"

        val password = "123456"

        val mock = User(email, password)

        BDDMockito.given(userRepository.save(any())).willReturn(mock)

        val dto = UserDTO(email, password)

        val entity = userService?.signUp(dto)

        BDDMockito.then(userRepository).should(times(1)).save(any())

        Assertions.assertEquals(mock.getEmail(), entity?.getEmail())
        Assertions.assertEquals(mock.getPassword(), entity?.getPassword())
    }
}