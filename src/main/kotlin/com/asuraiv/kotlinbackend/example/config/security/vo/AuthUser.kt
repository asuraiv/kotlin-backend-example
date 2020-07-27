package com.asuraiv.kotlinbackend.example.config.security.vo

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class AuthUser(
    private val authorities: MutableCollection<out GrantedAuthority>,
    private val username: String?,
    private val password: String?,
    private val isEnabled: Boolean,
    private val isAccountNonExpired: Boolean,
    private val isAccountNonLocked: Boolean,
    private val isCredentialNonExpired: Boolean) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = this.authorities

    override fun isEnabled(): Boolean = this.isEnabled

    override fun getUsername(): String? = this.username

    override fun isCredentialsNonExpired(): Boolean = this.isCredentialNonExpired

    override fun getPassword(): String? = this.password

    override fun isAccountNonExpired(): Boolean = this.isAccountNonExpired

    override fun isAccountNonLocked(): Boolean = this.isAccountNonLocked
}