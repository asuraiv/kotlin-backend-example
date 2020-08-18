package com.asuraiv.kotlinbackend.example.domain.user

import com.asuraiv.kotlinbackend.example.domain.user.dto.UserCreateRequest
import com.asuraiv.kotlinbackend.example.domain.user.dto.UserSearchResult
import com.asuraiv.kotlinbackend.example.domain.user.entity.User
import com.asuraiv.kotlinbackend.example.domain.user.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository
) {

    fun createUser(request: UserCreateRequest) {

        val user = User(
            userName = request.username,
            password = BCrypt.hashpw(request.password, BCrypt.gensalt()),
            email = request.email
        )

        userRepository.save(user)
    }

    fun getUser(username: String): UserSearchResult {

        val user = userRepository.findByUserName(username)

        user ?: throw RuntimeException("Not exist user. name: $username")

        return UserSearchResult(
            username = user.userName,
            email = user.email
        )
    }
}