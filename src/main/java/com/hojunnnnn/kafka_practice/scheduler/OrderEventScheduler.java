package com.hojunnnnn.kafka_practice.scheduler;

import com.hojunnnnn.kafka_practice.order.application.OrderEventOutboxService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderEventScheduler {

    private final OrderEventOutboxService orderEventOutboxService;

    /**
     * 실패한 이벤트를 재발행한다.
     */
    public void retryFailedEvents() {

    }

    /**
     * 발행된 오래된 이벤트를 삭제한다.
     */
    public void deletePublishedEvents() {

    }

}
