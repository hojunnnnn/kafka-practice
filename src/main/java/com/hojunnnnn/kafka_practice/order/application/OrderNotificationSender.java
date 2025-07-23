package com.hojunnnnn.kafka_practice.order.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.hojunnnnn.kafka_practice.common.utils.DelayUtils.randomDelay;

@Slf4j
@Service
public class OrderNotificationSender {

    @Transactional
    public void sendOrderCompleted(Long orderId) {
        // 예: 이메일, SMS, 푸시 알림 등
        log.info("🟢 sendOrderNotification : 주문 완료 알림 전송 시작, orderId={}", orderId);

        randomDelay();

        log.info("🟢 sendOrderNotification : 주문 완료 알림 전송 완료, orderId={}", orderId);
    }
}
