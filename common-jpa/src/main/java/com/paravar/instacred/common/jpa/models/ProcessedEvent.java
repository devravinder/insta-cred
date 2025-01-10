package com.paravar.instacred.common.jpa.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "processed_events")
@NoArgsConstructor
@AllArgsConstructor
public class ProcessedEvent {
    @Id
    @NotNull @Column(name = "event_id")
    private String eventId;

    @Column(name = "event_name")
    @NotNull private String eventName;

    @Column(name = "object_id")
    private Long objectId;
}
