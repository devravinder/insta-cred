package com.paravar.instacred.creditScore.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "processed_events")
@AllArgsConstructor
@NoArgsConstructor
public class ProcessedEvent {
    @Id
    @Field("event_id")
    private String eventId;

    @Field("event_name")
    private String eventName;

    @Field("object_id")
    private Long objectId;
}
