package com.paravar.instacred.creditScore.domain;

public interface EventTrackerService {
    void logEvent(String eventId, String eventName, Long objectId);

    boolean isEventProcessed(String eventId);
}
