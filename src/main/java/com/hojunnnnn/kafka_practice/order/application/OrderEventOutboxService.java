package com.hojunnnnn.kafka_practice.order.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderEventOutboxService {

    private final OrderEventOutboxManager orderEventOutboxManager;

    public void save(final Long orderId) {
        orderEventOutboxManager.save(orderId);
    }

    public void publishCompleted(final Long orderId) {
        orderEventOutboxManager.published(orderId);
    }

    public void publishFailed(final Long orderId) {
        orderEventOutboxManager.failed(orderId);
    }
}
