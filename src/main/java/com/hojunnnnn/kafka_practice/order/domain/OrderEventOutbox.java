package com.hojunnnnn.kafka_practice.order.domain;

import com.hojunnnnn.kafka_practice.common.domain.BaseTimeEntity;
import com.hojunnnnn.kafka_practice.order.domain.type.EventStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderEventOutbox extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "event_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus;

    public OrderEventOutbox(Long orderId) {
        this.orderId = orderId;
        this.eventStatus = EventStatus.INIT;
    }

    public void published() {
        this.eventStatus = EventStatus.PUBLISHED;
    }

    public void failed() {
        this.eventStatus = EventStatus.FAILED;
    }
}