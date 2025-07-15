package com.hojunnnnn.kafka_practice.order.infra;

import com.hojunnnnn.kafka_practice.order.domain.OrderEventOutbox;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderEventOutboxRepository extends JpaRepository<OrderEventOutbox, Long> {

    Optional<OrderEventOutbox> findByOrderId(Long orderId);
}
