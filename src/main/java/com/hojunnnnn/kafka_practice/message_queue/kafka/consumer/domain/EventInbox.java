package com.hojunnnnn.kafka_practice.message_queue.kafka.consumer.domain;

import com.hojunnnnn.kafka_practice.common.domain.BaseTimeEntity;
import com.hojunnnnn.kafka_practice.message_queue.kafka.consumer.domain.type.InboxStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"event_id", "consumer_id"}))
@Entity
public class EventInbox extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_id", nullable = false)
    private String eventId;

    @Column(name = "consumer_id", nullable = false)
    private String consumerId;

    @Column(name = "inbox_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private InboxStatus inboxStatus;

    @Column(name = "event_type", nullable = false)
    private String eventType;

    public EventInbox(String eventId, String consumerId, InboxStatus inboxStatus, String eventType) {
        this.eventId = eventId;
        this.consumerId = consumerId;
        this.inboxStatus = inboxStatus;
        this.eventType = eventType;
    }
}

