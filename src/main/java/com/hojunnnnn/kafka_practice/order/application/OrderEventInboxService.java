package com.hojunnnnn.kafka_practice.order.application;

import com.hojunnnnn.kafka_practice.order.domain.OrderEventInbox;
import com.hojunnnnn.kafka_practice.order.infra.OrderEventInboxRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.hojunnnnn.kafka_practice.message_queue.kafka._const.KafkaConst.ConsumerGroup.ORDER_CONSUMER_GROUP_0;
import static com.hojunnnnn.kafka_practice.order.domain.type.InboxStatus.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderEventInboxService {

    private final OrderNotificationSender orderNotificationSender;
    private final OrderEventInboxRepository orderEventInboxRepository;


    @Transactional
    public void processOrderEvent(final Long orderId) {
        // 1. 이미 처리된 이벤트인지 확인
        String eventId = String.valueOf(orderId);
        if(orderEventInboxRepository.existsByEventIdAndConsumerId(eventId, ORDER_CONSUMER_GROUP_0)) {
            log.info("🟠 이미 처리된 이벤트 입니다. eventId={}, consumerId={}", eventId, ORDER_CONSUMER_GROUP_0);
            return;
        }

        // 2. EventInbox 에 이벤트 기록
        OrderEventInbox eventInbox = new OrderEventInbox(
                eventId,
                ORDER_CONSUMER_GROUP_0,
                PROCESSING,
                "ORDER_EVENT");
        OrderEventInbox savedInbox = orderEventInboxRepository.save(eventInbox);

        // 3. 비즈니스 로직 처리
        orderNotificationSender.sendOrderCompleted(orderId);

        // 4. 처리 완료 후 EventInbox 상태 업데이트
        savedInbox.completed();
        orderEventInboxRepository.save(savedInbox);
    }
}
