package com.asuraiv.kotlinbackend.example.config.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.core.io.ClassPathResource
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.provider.ClientDetailsService
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory
import java.io.IOException

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
        return JwtTokenStore(tokenConverter())
    }

    @Primary
    @Bean
    fun tokenConverter(): JwtAccessTokenConverter {
        val converter = JwtAccessTokenConverter()
        try {
            val keyFactory = KeyStoreKeyFactory(ClassPathResource("server.jks"), "changeme".toCharArray())
            val keyPair = keyFactory.getKeyPair("example_auth", "changeme".toCharArray())
            converter.setKeyPair(keyPair)
            return converter
        } catch (e: IOException) {
            throw SecurityException("Error occur during create token converter", e)
        }
    }
}