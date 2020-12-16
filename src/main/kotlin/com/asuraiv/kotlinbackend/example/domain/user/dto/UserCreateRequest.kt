package com.asuraiv.kotlinbackend.example.domain.user.dto

class UserCreateRequest(
    val userName: String,
    val password: String,
    val address: String = "",
    val email: String = "",
    val phone: String = "")