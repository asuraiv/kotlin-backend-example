package com.asuraiv.kotlinbackend.example.domain.post.vo

import java.util.*

data class Post(
    val id: Long? = null,
    var title: String = "",
    var content: String = "",
    var writer: String = "",
    val createdAt: Date = Date(),
    val modifiedAt: Date = Date()
)