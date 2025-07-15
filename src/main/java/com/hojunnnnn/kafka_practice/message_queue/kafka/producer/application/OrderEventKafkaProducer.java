package com.hojunnnnn.kafka_practice.message_queue.kafka.producer.application;

import com.hojunnnnn.kafka_practice.order.application.OrderCompletedEvent;
import com.hojunnnnn.kafka_practice.order.application.OrderEventOutboxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static com.hojunnnnn.kafka_practice.message_queue.kafka._const.KafkaConst.Topics.FCT_ORDER_COMPLETED;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderEventKafkaProducer {

    private final KafkaTemplate<String, String> kafkaDefaultTemplate;
    private final OrderEventOutboxService orderEventOutboxService;

    public void publishOrderCompletedEvent(final OrderCompletedEvent event) {
        kafkaDefaultTemplate
                .send(FCT_ORDER_COMPLETED, event.orderId().toString())
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        log.info("🟢 카프카 이벤트 발행 성공, orderId={} with offset={}",
                                event.orderId(), result.getRecordMetadata().offset());
                        orderEventOutboxService.publishCompleted(event.orderId());
                        log.info("🟢 EventOutbox PUBLISHED 업데이트 완료, orderId={}", event.orderId());
                    } else {
                        log.error("🔴 카프카 이벤트 발행 실패, orderId={} due to: {}",
                                event.orderId(), ex.getMessage());
                        orderEventOutboxService.publishFailed(event.orderId());
                        log.error("🔴 EventOutbox FAILED 업데이트 완료, orderId={}", event.orderId());
                    }
                });
    }
}
