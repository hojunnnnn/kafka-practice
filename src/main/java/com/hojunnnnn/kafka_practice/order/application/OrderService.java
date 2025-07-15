package com.hojunnnnn.kafka_practice.order.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.hojunnnnn.kafka_practice.common.utils.DelayUtils.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderManager orderManager;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public void createOrder(final Long orderId) {
        // 1. 도메인 로직 수행
        log.info("🟢 createOrder : 주문 처리 시작, orderId={}", orderId);
        randomDelay();
        orderManager.save(orderId);
        log.info("🟢 createOrder : 주문 처리 완료, orderId={}", orderId);

        // 2. Outbox table 에 이벤트를 저장하여 작업의 원자성 보장
        applicationEventPublisher.publishEvent(new OrderEvent(orderId));

        // 3. 주문 완료 이벤트 발행
        applicationEventPublisher.publishEvent(new OrderCompletedEvent(orderId));

    }
}
