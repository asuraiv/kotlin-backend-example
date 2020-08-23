package com.asuraiv.kotlinbackend.example.domain.user

import com.asuraiv.kotlinbackend.example.domain.user.dto.UserCreateRequest
import com.asuraiv.kotlinbackend.example.domain.user.dto.UserSearchResult
import com.asuraiv.kotlinbackend.example.domain.user.entity.User
import com.asuraiv.kotlinbackend.example.domain.user.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository
) {

    val log: Logger = LoggerFactory.getLogger(UserService::class.java)

    fun deleteUser(username: String) {

        userRepository.deleteById(username)

        log.info("Delete user complete. username: $username")
    }

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