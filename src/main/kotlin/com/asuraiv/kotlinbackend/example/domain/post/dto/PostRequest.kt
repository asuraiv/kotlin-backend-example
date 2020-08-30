package com.asuraiv.kotlinbackend.example.domain.post.dto

import java.lang.RuntimeException

class PostRequest(
    val searchKey: String,
    val searchType: String,
    val page: Int,
    val size: Int
) {

    fun validate() {

        if(page < 0) {
            throw RuntimeException("'page' can't less then 0")
        }
        if(page == null) {
            throw RuntimeException("'page' is required.")
        }
        if(size < 0) {
            throw RuntimeException("'page' can't less then 0")
        }
        if(size == null) {
            throw RuntimeException("'page' is required.")
        }
    }
}