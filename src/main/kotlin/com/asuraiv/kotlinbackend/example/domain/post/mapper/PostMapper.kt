package com.asuraiv.kotlinbackend.example.domain.post.mapper

import com.asuraiv.kotlinbackend.example.domain.post.vo.Post
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

@Repository
@Mapper
interface PostMapper {

    fun insertPost(post: Post)
}