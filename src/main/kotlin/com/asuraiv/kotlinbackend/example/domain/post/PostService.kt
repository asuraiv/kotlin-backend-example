package com.asuraiv.kotlinbackend.example.domain.post

import com.asuraiv.kotlinbackend.example.domain.post.dto.PostCreateRequest
import com.asuraiv.kotlinbackend.example.domain.post.mapper.PostMapper
import com.asuraiv.kotlinbackend.example.domain.post.vo.Post
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class PostService(
    val postMapper: PostMapper
) {

    val log: Logger = LoggerFactory.getLogger(PostService::class.java)

    fun createPost(request: PostCreateRequest) {

        val post = Post(title = request.title,
            content = request.content,
            writer = request.writer)

        postMapper.insertPost(post)
        log.info("Create post. id: ${post.id}, title: ${post.title}, writer: ${post.writer}")
    }

    fun getPost(id: Long?): Post {

        id ?: throw RuntimeException("Post 'id' is required.")

        return postMapper.findPostById(id)
    }
}