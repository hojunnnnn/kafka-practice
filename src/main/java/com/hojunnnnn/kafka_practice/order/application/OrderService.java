package com.hojunnnnn.kafka_practice.order.application;

import com.hojunnnnn.kafka_practice.order.domain.Order;
import com.hojunnnnn.kafka_practice.order.infra.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public void createOrder(final Long orderId) {
        // 1. 도메인 로직 수행
        log.info("🟢 createOrder : 주문 처리 시작, orderId={}", orderId);

        orderRepository.save(new Order(orderId));

        log.info("🟢 createOrder : 주문 처리 완료, orderId={}", orderId);

        // TODO 2. Outbox table 에 이벤트를 저장하여 작업의 원자성 보장

        // TODO 3. 주문 완료 이벤트 발행
    }
}
