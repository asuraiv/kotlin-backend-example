package com.asuraiv.kotlinbackend.example.domain.post.vo

import java.util.*

data class Post(
    val id: Long? = null,
    val title: String = "",
    val content: String = "",
    val writer: String = "",
    val createdAt: Date = Date(),
    val modifiedAt: Date = Date()
)