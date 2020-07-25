package com.asuraiv.kotlinbackend.example.config.security

import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.jwk.JwkTokenStore

@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfig(
    val authenticationManager: AuthenticationManager,
    val tokenStore: TokenStore
) : AuthorizationServerConfigurerAdapter() {


}