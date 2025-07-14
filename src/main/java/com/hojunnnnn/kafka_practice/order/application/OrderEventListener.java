package com.hojunnnnn.kafka_practice.order.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderEventListener {

    private final OrderEventOutboxManager orderEventOutboxManager;

    /**
     * 커밋 전(Before Commit)에 Outbox 를 저장한다.
     */
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void saveEventOutbox(final OrderEvent event) {
        log.info("🟢 saveEventOutbox : BEFORE_COMMIT 이벤트 수신 완료, orderId={}", event.orderId());
        orderEventOutboxManager.save(event.orderId());
        log.info("🟢 saveEventOutbox : BEFORE_COMMIT 이벤트 저장 완료, orderId={}", event.orderId());
    }


}
