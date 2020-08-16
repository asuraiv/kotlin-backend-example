package com.asuraiv.kotlinbackend.example.domain.user

import com.asuraiv.kotlinbackend.example.domain.user.dto.UserDto
import com.asuraiv.kotlinbackend.example.domain.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository
) {

    fun getUser(username: String): UserDto {

        val user = userRepository.findByUserName(username)

        user ?: throw RuntimeException("Not exist user. name: $username")

        return UserDto(
            username = user.userName,
            email = user.email
        )
    }
}