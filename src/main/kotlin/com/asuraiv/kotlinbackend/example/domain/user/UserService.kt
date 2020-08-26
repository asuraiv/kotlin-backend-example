package com.asuraiv.kotlinbackend.example.domain.user

import com.asuraiv.kotlinbackend.example.domain.user.constant.UserType
import com.asuraiv.kotlinbackend.example.domain.user.dto.UserCreateRequest
import com.asuraiv.kotlinbackend.example.domain.user.dto.UserSearchResult
import com.asuraiv.kotlinbackend.example.domain.user.entity.Person
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

    fun createPerson(request: UserCreateRequest) {

        val person = Person()
        person.userName = request.username
        person.password = BCrypt.hashpw(request.password, BCrypt.gensalt())
        person.address = request.address
        person.email = request.email
        person.phone = request.phone

        userRepository.save(person)
    }

    fun getUser(username: String): UserSearchResult {

        val user = userRepository.findByUserName(username)

        user ?: throw RuntimeException("Not exist user. name: $username")

        return when(user) {
            is Person -> {
                UserSearchResult(
                    username = user.userName,
                    userType = UserType.PERSON,
                    address = user.address,
                    email = user.email,
                    phone = user.phone
                )
            }
            else -> throw RuntimeException("Not supported user type. ${user.javaClass.name}")
        }
    }
}