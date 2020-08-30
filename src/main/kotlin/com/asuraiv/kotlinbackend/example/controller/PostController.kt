package com.asuraiv.kotlinbackend.example.controller

import com.asuraiv.kotlinbackend.example.domain.post.PostService
import com.asuraiv.kotlinbackend.example.domain.post.dto.PostCreateRequest
import com.asuraiv.kotlinbackend.example.domain.post.dto.PostRequest
import com.asuraiv.kotlinbackend.example.domain.post.vo.Post
import org.springframework.web.bind.annotation.*

@RestController
class PostController(
    val postService: PostService
) {

    @PostMapping("/posts")
    fun createPost(@RequestBody request: PostCreateRequest) {
        postService.createPost(request)
    }

    @GetMapping("/posts/{id}")
    fun getPost(@PathVariable id: Long): Post {
        return postService.getPost(id)
    }

    @GetMapping("/posts")
    fun getPostList(@RequestBody request: PostRequest): List<Post> {
        return postService.getPostList(request)
    }
}