package com.asuraiv.kotlinbackend.example.controller

import com.asuraiv.kotlinbackend.example.domain.user.UserService
import com.asuraiv.kotlinbackend.example.domain.user.dto.UserCreateRequest
import com.asuraiv.kotlinbackend.example.domain.user.dto.UserSearchResult
import org.springframework.web.bind.annotation.*
import javax.annotation.security.RolesAllowed

@RestController
class UserController(
    val userService: UserService
) {
    @PostMapping("/users")
    fun createUser(@RequestBody request: UserCreateRequest) {
        userService.createPerson(request)
    }

    @GetMapping("/users/{username}")
    fun getUser(@PathVariable username: String): UserSearchResult {
        return userService.getUser(username)
    }

    @RolesAllowed("USER_ADMIN")
    @DeleteMapping("/users/{username}")
    fun deleteUser(@PathVariable username: String) {
        userService.deleteUser(username)
    }
}