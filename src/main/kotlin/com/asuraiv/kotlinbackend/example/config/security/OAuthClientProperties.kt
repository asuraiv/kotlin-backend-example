package com.asuraiv.kotlinbackend.example.config.security

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "oauth")
class OAuthClientProperties(
    val client: String,
    val secret: String,
    val grantTypes: String,
    val authorities: String,
    val scopes: String,

    val accessTokenValiditySeconds: Int,
    val refreshTokenValiditySeconds: Int
)