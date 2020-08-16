package com.asuraiv.kotlinbackend.example.domain.user.dto

data class UserDto(
    val username: String,
    val email: String = ""
)