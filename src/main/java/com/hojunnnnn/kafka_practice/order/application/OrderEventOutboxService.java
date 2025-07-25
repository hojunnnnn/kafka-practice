package com.hojunnnnn.kafka_practice.order.application;

import com.hojunnnnn.kafka_practice.message_queue.kafka.producer.application.OrderEventKafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@RequiredArgsConstructor
@Service
public class OrderEventOutboxService {

    private final OrderEventOutboxManager orderEventOutboxManager;
    private final OrderEventKafkaProducer orderEventKafkaProducer;

    public void save(final Long orderId) {
        orderEventOutboxManager.save(orderId);
    }

    public void retryFailedEvents() {
        orderEventOutboxManager.getFailedEvents()
                .forEach(event ->  {
                    orderEventKafkaProducer.publishOrderCompletedEvent(new OrderCompletedEvent(event.getOrderId()));
                });
    }

    public void deleteOldPublishedEvents() {
        orderEventOutboxManager.deletePublishedEvent();
    }
}
