package com.paravar.instacred.common.jpa;

import com.paravar.instacred.common.jpa.models.Event;
import java.util.Optional;

public interface EventTrackerService {
    void logEvent(String eventId, String eventName, Long objectId);

    Optional<Event> get(String eventId, String eventName);
}
