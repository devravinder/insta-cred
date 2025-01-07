package com.paravar.instacred.loanProcess.domain;

import com.paravar.instacred.loanProcess.domain.models.ProcessedEvent;
import java.util.Optional;

public interface EventTrackerService {
    void create(String loanRequestEventId);

    Optional<ProcessedEvent> get(String loanRequestEventId);
}
