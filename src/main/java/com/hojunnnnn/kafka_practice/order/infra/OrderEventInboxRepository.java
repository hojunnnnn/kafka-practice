package com.hojunnnnn.kafka_practice.order.infra;

import com.hojunnnnn.kafka_practice.order.domain.OrderEventInbox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderEventInboxRepository extends JpaRepository<OrderEventInbox, Long> {

    boolean existsByEventIdAndConsumerId(String eventId, String consumerId);

}
