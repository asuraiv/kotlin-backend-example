package com.asuraiv.kotlinbackend.example.config.security

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "oauth")
class OAuthClientProperties(

    var client: String = "",
    var secret: String = "",
    var grantTypes: String = "",
    var authorities: String = "",
    var scopes: String = "",

    var accessTokenValiditySeconds: Int = 0,
    var refreshTokenValiditySeconds: Int = 0
)