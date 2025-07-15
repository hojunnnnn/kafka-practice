package com.hojunnnnn.kafka_practice.order.application;

import com.hojunnnnn.kafka_practice.message_queue.kafka.producer.application.OrderEventKafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderEventListener {

    private final OrderEventOutboxService orderEventOutboxService;
    private final OrderEventKafkaProducer orderEventKafkaProducer;

    /**
     * 커밋 전(Before Commit)에 Outbox 를 저장한다.
     */
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void saveEventOutbox(final OrderEvent event) {
        log.info("🟢 saveEventOutbox : BEFORE_COMMIT 이벤트 수신 완료, orderId={}", event.orderId());
        orderEventOutboxService.save(event.orderId());
        log.info("🟢 saveEventOutbox : BEFORE_COMMIT 이벤트 저장 완료, orderId={}", event.orderId());
    }

    /**
     * 커밋 후(After Commit)에 주문 완료 이벤트를 처리한다.
     */
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleOrderCompletedEvent(final OrderCompletedEvent event) {
        log.info("🟢 handleOrderCompletedEvent : AFTER_COMMIT 이벤트 수신 완료, orderId={}", event.orderId());
        orderEventKafkaProducer.publishOrderCompletedEvent(event);
        log.info("🟢 handleOrderCompletedEvent : AFTER_COMMIT 이벤트 처리 완료, orderId={}", event.orderId());
    }
}
