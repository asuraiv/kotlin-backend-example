package com.asuraiv.kotlinbackend.example.controller

import com.asuraiv.kotlinbackend.example.domain.post.PostService
import com.asuraiv.kotlinbackend.example.domain.post.dto.PostCreateRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PostController(
    val postService: PostService
) {

    @PostMapping("/posts")
    fun createPost(@RequestBody request: PostCreateRequest) {
        postService.createPost(request)
    }
}