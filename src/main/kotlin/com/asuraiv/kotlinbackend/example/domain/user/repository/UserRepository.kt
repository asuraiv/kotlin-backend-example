package com.asuraiv.kotlinbackend.example.domain.user.repository

import com.asuraiv.kotlinbackend.example.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, String> {
}