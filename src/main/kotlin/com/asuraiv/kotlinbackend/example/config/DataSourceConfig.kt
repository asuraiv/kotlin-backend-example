package com.asuraiv.kotlinbackend.example.config

import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import javax.sql.DataSource

@EnableJpaRepositories(basePackages = ["com.asuraiv.kotlinbackend.example.domain"])
@EntityScan(basePackages = ["com.asuraiv.kotlinbackend.example.domain"])
@Configuration
class DataSourceConfig {

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.sample")
    fun dataSource(): DataSource {

        return DataSourceBuilder
                .create()
                .type(HikariDataSource::class.java)
                .build()
    }
}