package com.asuraiv.kotlinbackend.example.domain.user.entity

import javax.persistence.*

@Entity
@Table(name = "user", catalog = "example")
@DiscriminatorColumn(name = "userType")
@Inheritance(strategy = InheritanceType.JOINED)
abstract class User {

    @Id
    @Column(name = "username")
    lateinit var userName: String

    @Column(name = "password")
    lateinit var password: String
}