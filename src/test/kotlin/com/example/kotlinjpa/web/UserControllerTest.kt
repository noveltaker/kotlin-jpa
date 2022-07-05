package com.example.kotlinjpa.web

import com.example.kotlinjpa.domain.User
import com.example.kotlinjpa.service.UserService
import com.example.kotlinjpa.service.dto.UserDTO
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito
import org.mockito.internal.verification.VerificationModeFactory.times
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders


@ExtendWith(SpringExtension::class)
@WebMvcTest(value = [UserController::class])
@AutoConfigureMockMvc
internal class UserControllerTest {

    private var mockMcv: MockMvc? = null

    @MockBean
    private lateinit var userService: UserService


    @BeforeEach
    fun init() {
        mockMcv = MockMvcBuilders.standaloneSetup(UserController(userService)).build()
    }

    @Test
    fun `회`() {

        val mockEmail = "test@naver.com"

        val mockPassword = "123456"

        val mock = User(mockEmail, mockPassword)

        val dto = UserDTO(mockEmail, mockPassword)

        val objectMapper = ObjectMapper()

        val content = objectMapper.writeValueAsString(dto)

        BDDMockito.given(userService.signUp(dto)).willReturn(mock)

        val action = mockMcv?.perform(
            post("/signup")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
        )?.andDo(print())

        BDDMockito.then(userService).should(times(1)).signUp(dto)

        action?.andExpect(status().isCreated)
            ?.andExpect(jsonPath("$['email']").value(mockEmail))
            ?.andExpect(jsonPath("$['password']").value(mockPassword))

    }

}
