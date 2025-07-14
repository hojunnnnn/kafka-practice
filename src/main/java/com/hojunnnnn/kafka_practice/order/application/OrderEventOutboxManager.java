package com.hojunnnnn.kafka_practice.order.application;

import com.hojunnnnn.kafka_practice.order.domain.OrderEventOutbox;
import com.hojunnnnn.kafka_practice.order.infra.OrderEventOutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@RequiredArgsConstructor
@Component
public class OrderEventOutboxManager {

    private final OrderEventOutboxRepository orderEventOutboxRepository;

    @Transactional
    public void save(final Long orderId) {
        final OrderEventOutbox outbox = new OrderEventOutbox(orderId);
        orderEventOutboxRepository.save(outbox);
    }
}
