package com.asuraiv.kotlinbackend.example.listener

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.TopicPartition
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.listener.ConsumerSeekAware
import org.springframework.stereotype.Component

@Component
class SampleTopicListener : ConsumerSeekAware {

    val log: Logger = LoggerFactory.getLogger(SampleTopicListener::class.java)

    @KafkaListener(topics = ["sample-topic"])
    fun consume(record: ConsumerRecord<String, String>) {

        log.info("Message: ${record.value()}")
    }

    override fun onIdleContainer(assignments: MutableMap<TopicPartition, Long>?, callback: ConsumerSeekAware.ConsumerSeekCallback?) {
    }

    /**
     * Consumer가 구동되면, 가장 마지막 offset의 메시지를 가져온다.
     */
    override fun onPartitionsAssigned(assignments: MutableMap<TopicPartition, Long>, callback: ConsumerSeekAware.ConsumerSeekCallback) {

        assignments.forEach { (topic, _) ->
            assignments[topic]?.minus(1L)?.let { callback.seek(topic.topic(), topic.partition(), it) }
        }
    }

    override fun registerSeekCallback(callback: ConsumerSeekAware.ConsumerSeekCallback?) {
    }
}