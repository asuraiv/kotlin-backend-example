package com.asuraiv.kotlinbackend.example.config

import com.zaxxer.hikari.HikariDataSource
import org.apache.ibatis.session.SqlSessionFactory
import org.mybatis.spring.SqlSessionFactoryBean
import org.mybatis.spring.annotation.MapperScan
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
// MyBatis Config
@MapperScan(
    basePackages = ["com.asuraiv.kotlinbackend.example"],
    sqlSessionFactoryRef = "sessionFactory"
)
// JPA Config
@EnableJpaRepositories(basePackages = ["com.asuraiv.kotlinbackend.example.domain"])
@EntityScan(basePackages = ["com.asuraiv.kotlinbackend.example.domain"])
@EnableTransactionManagement
class DataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.example")
    fun dataSource(): DataSource {

        return DataSourceBuilder
                .create()
                .type(HikariDataSource::class.java)
                .build()
    }

    @Bean
    fun sessionFactory(): SqlSessionFactory {

        val sqlSessionFactoryBean = SqlSessionFactoryBean()

        sqlSessionFactoryBean.setDataSource(dataSource())
        sqlSessionFactoryBean.setMapperLocations(PathMatchingResourcePatternResolver().getResources("classpath:/mapper/*.xml"))
        sqlSessionFactoryBean.vfs = SpringBootVFS::class.java

        return sqlSessionFactoryBean.`object`!!
    }
}