package com.asuraiv.kotlinbackend.example.config

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import java.net.InetAddress
import java.net.UnknownHostException
import java.util.*

@Configuration
class KafkaConfig {

    @Value("\${kafka.hosts}")
    lateinit var hosts: String

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, String> {

        val containerFactory = ConcurrentKafkaListenerContainerFactory<String, String>()
        containerFactory.consumerFactory = consumerFactory()

        return containerFactory
    }

    @Bean
    fun consumerFactory(): ConsumerFactory<String, String>? {

        return DefaultKafkaConsumerFactory(consumerProperties())
    }

    @Bean
    fun consumerProperties(): Map<String, Any> {
        val hostName = try {
            InetAddress.getLocalHost().hostName
        } catch (e: UnknownHostException) {
            UUID.randomUUID().toString()
        }

        return hashMapOf(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to hosts,
                ConsumerConfig.GROUP_ID_CONFIG to hostName,
                ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG to "true",
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to "latest",
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java
        )
    }
}