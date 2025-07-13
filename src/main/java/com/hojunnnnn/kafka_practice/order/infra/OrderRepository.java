package com.hojunnnnn.kafka_practice.order.infra;

import com.hojunnnnn.kafka_practice.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
