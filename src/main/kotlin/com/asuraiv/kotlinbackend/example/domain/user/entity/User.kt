package com.asuraiv.kotlinbackend.example.domain.user.entity

import com.asuraiv.kotlinbackend.example.domain.user.constant.UserType
import javax.persistence.*

@Entity
@Table(name = "user", catalog = "example")
@DiscriminatorColumn(name = "userType")
@Inheritance(strategy = InheritanceType.JOINED)
open class User {

    @Id
    @Column(name = "username")
    lateinit var userName: String

    @Column(name = "password")
    lateinit var password: String

    @Column(name = "userType")
    @Enumerated(EnumType.STRING)
    lateinit var userType: UserType
}