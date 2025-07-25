package com.hojunnnnn.kafka_practice.order.infra.kafka;

import com.hojunnnnn.kafka_practice.order.application.OrderCompletedEvent;

public interface OrderEventPublisher {

    void publishOrderCompletedEvent(OrderCompletedEvent event);

}
