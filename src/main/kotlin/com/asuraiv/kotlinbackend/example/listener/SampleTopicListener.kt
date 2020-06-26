package com.asuraiv.kotlinbackend.example.listener

import org.apache.kafka.clients.consumer.ConsumerRecords
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class SampleTopicListener {

    val log = LoggerFactory.getLogger(SampleTopicListener::class.java)

    @KafkaListener(topics = ["sample-topic"])
    fun consume(records: ConsumerRecords<String, String>) {

        log.info("Records count: ${records.count()}")
    }
}