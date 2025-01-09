package com.paravar.instacred.loanProcess.domain;

import com.paravar.instacred.loanProcess.domain.models.EventId;

import java.util.Optional;

public interface EventTrackerService {
    void create(String eventId, Long objectId);

    Optional<EventId> get(String eventId);
}
