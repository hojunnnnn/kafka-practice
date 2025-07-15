package com.hojunnnnn.kafka_practice.message_queue.kafka.producer.application;

import com.hojunnnnn.kafka_practice.order.application.OrderCompletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static com.hojunnnnn.kafka_practice.message_queue.kafka._const.KafkaConst.Topics.FCT_ORDER_COMPLETED;

@RequiredArgsConstructor
@Component
public class OrderEventKafkaProducer {

    private final KafkaTemplate<String, String> kafkaDefaultTemplate;

    public void publishOrderCompletedEvent(final OrderCompletedEvent event) {

        kafkaDefaultTemplate.send(FCT_ORDER_COMPLETED, event.orderId().toString());
    }
}
