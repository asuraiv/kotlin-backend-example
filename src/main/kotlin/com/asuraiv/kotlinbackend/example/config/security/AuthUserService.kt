package com.asuraiv.kotlinbackend.example.config.security

import com.asuraiv.kotlinbackend.example.config.security.vo.AuthUser
import com.asuraiv.kotlinbackend.example.domain.user.entity.User
import com.asuraiv.kotlinbackend.example.domain.user.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthUserService(
    val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = userRepository.findByUserName(username!!)
        return buildOAuthUser(user)
    }

    fun buildOAuthUser(user: User?): AuthUser {

        return AuthUser(
            username = user?.userName,
            password = user?.password,
            isEnabled = true,
            isAccountNonExpired = true,
            isAccountNonLocked = true,
            isCredentialNonExpired = true,
            authorities = mutableListOf(SimpleGrantedAuthority("ROLE_USER"))
        )
    }
}