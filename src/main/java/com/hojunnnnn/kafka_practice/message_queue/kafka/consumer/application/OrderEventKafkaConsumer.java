package com.hojunnnnn.kafka_practice.message_queue.kafka.consumer.application;

import com.hojunnnnn.kafka_practice.order.application.OrderEventInboxService;
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

    private final OrderEventInboxService orderEventInboxService;

    @KafkaListener(
            topics = FCT_ORDER_COMPLETED,
            groupId = ORDER_CONSUMER_GROUP_0,
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void messageListener(ConsumerRecord<String, String> record, Acknowledgment ack) {
        try {
            log.info("🟢 컨슈머 이벤트 수신 완료, value={}, offset={}", record.value(), record.offset());
            orderEventInboxService.processOrderEvent(Long.parseLong(record.value()));
            log.info("🟢 컨슈머 로직 처리 완료, value={}, offset={}", record.value(), record.offset());

            // 모든 처리가 완료된 후 Kafka 에 ACK 전송
            ack.acknowledge();
            log.info("🟢 카프카 ACK 전송 완료, offset={}", record.offset());
        } catch (Exception e) {
            // 예외 발생 시 ACK 전송하지 않음
            log.error("🔴 컨슈머 이벤트 처리 중 오류 발생, value={}, offset={}, error={}", record.value(), record.offset(), e.getMessage());
            // 세밀한 제어가 필요할 경우 재처리 로직을 추가(DLQ, 재시도 토픽 등)
        }
    }
}
