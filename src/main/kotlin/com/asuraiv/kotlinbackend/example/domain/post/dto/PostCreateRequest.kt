package com.asuraiv.kotlinbackend.example.domain.post.dto

data class PostCreateRequest(
    val writer: String,
    val title: String,
    val content: String
)