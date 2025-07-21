package com.hojunnnnn.kafka_practice.scheduler;

import com.hojunnnnn.kafka_practice.order.application.OrderEventOutboxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderEventScheduler {

    private final OrderEventOutboxService orderEventOutboxService;

    /**
     * 실패한 이벤트를 재발행한다.
     * 조건 > INIT 또는 FAILED 상태의 이벤트
     *     > createDateTime 이 현재 시간 기준 5분 이상 지난 이벤트
     */
    @Scheduled(fixedDelay = 60000)
    public void retryFailedEvents() {
        log.info("🟢 Retry Failed Order Events Scheduler Executed");
        orderEventOutboxService.retryFailedEvents();
    }

    /**
     * 발행된 이벤트를 삭제한다.
     * 조건 > PUBLISHED 상태의 이벤트
     *      > createDateTime 이 현재 시간 기준 7일 이상 지난 이벤트
     */
    @Scheduled(fixedDelay =  60000)
    public void deletePublishedEvents() {
        log.info("🟢 Delete Published Order Events Scheduler Executed");
        orderEventOutboxService.deleteOldPublishedEvents();
    }

}
