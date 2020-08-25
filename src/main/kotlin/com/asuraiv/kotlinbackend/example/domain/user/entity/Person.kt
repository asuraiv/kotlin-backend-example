package com.asuraiv.kotlinbackend.example.domain.user.entity

import javax.persistence.*

@Entity
@Table(name = "person", catalog = "example")
@DiscriminatorValue("PERSON")
@PrimaryKeyJoinColumn(name = "username")
class Person : User() {

    @Column(name  = "address")
    lateinit var address: String

    @Column(name = "phone")
    lateinit var phone: String

    @Column(name = "email")
    lateinit var email: String
}