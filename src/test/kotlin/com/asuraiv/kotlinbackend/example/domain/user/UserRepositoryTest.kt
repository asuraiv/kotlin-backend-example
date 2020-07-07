package com.asuraiv.kotlinbackend.example.domain.user

import com.asuraiv.kotlinbackend.example.KotlinBackendApplication
import com.asuraiv.kotlinbackend.example.domain.user.entity.User
import com.asuraiv.kotlinbackend.example.domain.user.repository.UserRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [KotlinBackendApplication::class])
class UserRepositoryTest {

    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    @Transactional
    fun testCreate() {

        val user = User(userName = "asuraiv", password = "1234", email = "asuraiv@myemail.com")

        userRepository.save(user)
    }
}