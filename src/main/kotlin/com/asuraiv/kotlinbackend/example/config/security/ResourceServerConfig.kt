package com.asuraiv.kotlinbackend.example.config.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.core.annotation.Order
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.provider.ClientDetailsService
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore

@Configuration
@EnableResourceServer
class ResourceServerConfig(
    val authenticationManager: AuthenticationManager
) : ResourceServerConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {

        http
            .csrf().disable()
                .authorizeRequests()
                .antMatchers("/oauth/**")
                .permitAll()
            .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/users")
                .permitAll()
            .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
    }

    @Bean
    fun tokenService(clientDetailsService: ClientDetailsService): DefaultTokenServices {

        val tokenServices = DefaultTokenServices()
        tokenServices.setSupportRefreshToken(true)
        tokenServices.setTokenStore(tokenStore())
        tokenServices.setClientDetailsService(clientDetailsService)
        tokenServices.setAuthenticationManager(authenticationManager)

        return tokenServices
    }

    @Primary
    @Bean
    fun tokenStore(): JwtTokenStore {
        return JwtTokenStore(JwtAccessTokenConverter())
    }

    @Bean
    fun tokenConverter(): JwtAccessTokenConverter {
        return JwtAccessTokenConverter()
    }
}