package com.asuraiv.kotlinbackend.example.controller

import com.asuraiv.kotlinbackend.example.domain.user.UserService
import com.asuraiv.kotlinbackend.example.domain.user.dto.UserCreateRequest
import com.asuraiv.kotlinbackend.example.domain.user.dto.UserSearchResult
import org.springframework.web.bind.annotation.*

@RestController
class UserController(
    val userService: UserService
) {
    @PostMapping("/users")
    fun createUser(@RequestBody request: UserCreateRequest) {
        userService.createUser(request)
    }

    @GetMapping("/users/{username}")
    fun getUser(@PathVariable username: String): UserSearchResult {
        return userService.getUser(username)
    }
}