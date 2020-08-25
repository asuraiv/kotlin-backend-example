package com.asuraiv.kotlinbackend.example.domain.user.dto

import com.asuraiv.kotlinbackend.example.domain.user.constant.UserType

data class UserSearchResult(
    val username: String,
    val userType: UserType,
    val address: String = "",
    val email: String = "",
    val phone: String = ""
)