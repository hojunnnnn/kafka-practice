package com.hojunnnnn.kafka_practice.order.infra;

import com.hojunnnnn.kafka_practice.order.domain.OrderEventOutbox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderEventOutboxRepository extends JpaRepository<OrderEventOutbox, Long> {
}
