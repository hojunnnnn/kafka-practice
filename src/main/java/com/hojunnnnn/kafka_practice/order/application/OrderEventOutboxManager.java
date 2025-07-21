package com.hojunnnnn.kafka_practice.order.application;

import com.hojunnnnn.kafka_practice.order.domain.OrderEventOutbox;
import com.hojunnnnn.kafka_practice.order.infra.OrderEventOutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
public class OrderEventOutboxManager {

    private final OrderEventOutboxRepository orderEventOutboxRepository;

    @Transactional
    public void save(final Long orderId) {
        final OrderEventOutbox outbox = new OrderEventOutbox(orderId);
        orderEventOutboxRepository.save(outbox);
    }

    @Transactional
    public void published(final Long orderId) {
        orderEventOutboxRepository.findByOrderId(orderId)
                .ifPresent(OrderEventOutbox::published);
    }

    @Transactional
    public void failed(final Long orderId) {
        orderEventOutboxRepository.findByOrderId(orderId)
                .ifPresent(OrderEventOutbox::failed);
    }

    @Transactional(readOnly = true)
    public List<OrderEventOutbox> getFailedEvents() {
        return orderEventOutboxRepository.findAllFailedEvents(LocalDateTime.now().minusMinutes(5));
    }
}
