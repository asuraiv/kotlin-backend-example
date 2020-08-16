package com.asuraiv.kotlinbackend.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafka

//@EnableKafka
@SpringBootApplication(exclude = [KafkaAutoConfiguration::class])
class KotlinBackendApplication

fun main(args: Array<String>) {

    runApplication<KotlinBackendApplication>(*args)
}