package com.asuraiv.kotlinbackend.example.domain.user.dto

import com.asuraiv.kotlinbackend.example.domain.user.constant.UserType

data class UserSearchResult(
    var userName: String = "",
    var userType: UserType = UserType.NONE,
    var address: String = "",
    var email: String = "",
    var phone: String = ""
)