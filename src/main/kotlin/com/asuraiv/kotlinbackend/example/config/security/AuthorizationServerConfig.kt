package com.asuraiv.kotlinbackend.example.config.security

import com.asuraiv.kotlinbackend.example.domain.common.util.commaDelimitedListToStringArray
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter

@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfig(
    val authenticationManager: AuthenticationManager,
    val tokenStore: TokenStore,
    val jwtAccessTokenConverter: JwtAccessTokenConverter,
    val oAuthClientProperties: OAuthClientProperties
) : AuthorizationServerConfigurerAdapter() {


    override fun configure(security: AuthorizationServerSecurityConfigurer) {

        security
            .tokenKeyAccess("permitAll()")
            .checkTokenAccess("isAuthenticated()");
    }

    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients
            .inMemory()
            .withClient(oAuthClientProperties.client)
            .secret(BCrypt.hashpw(oAuthClientProperties.secret, BCrypt.gensalt()))
            .authorizedGrantTypes(*commaDelimitedListToStringArray(oAuthClientProperties.grantTypes))
            .authorities(*commaDelimitedListToStringArray(oAuthClientProperties.authorities))
            .scopes(*commaDelimitedListToStringArray(oAuthClientProperties.scopes))
            .accessTokenValiditySeconds(oAuthClientProperties.accessTokenValiditySeconds)
            .refreshTokenValiditySeconds(oAuthClientProperties.refreshTokenValiditySeconds)
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        endpoints
            .authenticationManager(authenticationManager)
            .accessTokenConverter(jwtAccessTokenConverter)
            .tokenStore(tokenStore)
    }
}