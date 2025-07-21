package com.hojunnnnn.kafka_practice.order.infra;

import com.hojunnnnn.kafka_practice.order.domain.OrderEventOutbox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderEventOutboxRepository extends JpaRepository<OrderEventOutbox, Long> {

    Optional<OrderEventOutbox> findByOrderId(Long orderId);

    @Query("SELECT oeo " +
            "FROM OrderEventOutbox oeo " +
            "WHERE (oeo.eventStatus = 'FAILED' OR oeo.eventStatus = 'INIT') " +
            "AND oeo.createdDateTime < :dateTime")
    List<OrderEventOutbox> findAllFailedEvents(LocalDateTime dateTime);
}
