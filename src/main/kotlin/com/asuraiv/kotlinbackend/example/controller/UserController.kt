package com.asuraiv.kotlinbackend.example.controller

import com.asuraiv.kotlinbackend.example.config.security.AuthUserService
import com.asuraiv.kotlinbackend.example.domain.user.UserService
import com.asuraiv.kotlinbackend.example.domain.user.dto.UserDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    val userService: UserService
) {

    @GetMapping("/example/users/{username}")
    fun getUser(@PathVariable username: String): UserDto {
        return userService.getUser(username)
    }
}