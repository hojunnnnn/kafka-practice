package com.hojunnnnn.kafka_practice.message_queue.kafka.consumer.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import static com.hojunnnnn.kafka_practice.message_queue.kafka._const.KafkaConst.ConsumerGroup.ORDER_CONSUMER_GROUP_0;
import static com.hojunnnnn.kafka_practice.message_queue.kafka._const.KafkaConst.Topics.FCT_ORDER_COMPLETED;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderEventKafkaConsumer {

    @KafkaListener(
            topics = FCT_ORDER_COMPLETED,
            groupId = ORDER_CONSUMER_GROUP_0,
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void messageListener(ConsumerRecord<String, String> record, Acknowledgment ack) {
        log.info("🟢 카프카 이벤트 수신 완료, value={}, offset={}", record.value(), record.offset());
        ack.acknowledge();
    }
}
