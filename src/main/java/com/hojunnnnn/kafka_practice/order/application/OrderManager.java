package com.hojunnnnn.kafka_practice.order.application;

import com.hojunnnnn.kafka_practice.order.domain.Order;
import com.hojunnnnn.kafka_practice.order.infra.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class OrderManager {

    private final OrderRepository orderRepository;

    @Transactional
    public void save(final Long orderId) {
        final Order order = new Order(orderId);
        orderRepository.save(order);
    }
}
