package com.asuraiv.kotlinbackend.example.domain.user.dto

data class UserSearchResult(
    val username: String,
    val email: String = ""
)