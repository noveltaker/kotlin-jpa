package com.example.kotlinjpa.service

import com.example.kotlinjpa.config.exception.SameEmailException
import com.example.kotlinjpa.domain.User
import com.example.kotlinjpa.repository.UserRepository
import com.example.kotlinjpa.service.dto.UserDTO
import org.springframework.stereotype.Service

@Service
class UserServiceImpl constructor(private val userRepository: UserRepository) : UserService {

    override fun signUp(dto: UserDTO): User = userRepository.existsByEmail(dto.getEmail()).let {
        if (it) {
            throw SameEmailException()
        }
        userRepository.save(dto.toEntity())
    }
}