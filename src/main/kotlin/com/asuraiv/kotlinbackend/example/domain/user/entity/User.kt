package com.asuraiv.kotlinbackend.example.domain.user.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user", catalog = "example")
data class User(
    @Id
    @Column(name = "username")
    val userName: String,

    @Column(name = "password")
    val password: String,

    @Column(name = "email")
    val email: String
)