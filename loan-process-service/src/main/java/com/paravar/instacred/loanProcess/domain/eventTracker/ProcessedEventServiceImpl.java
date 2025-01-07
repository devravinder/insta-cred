package com.paravar.instacred.loanProcess.domain.eventTracker;

import com.paravar.instacred.loanProcess.domain.EventTrackerService;
import com.paravar.instacred.loanProcess.domain.models.ProcessedEvent;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
class ProcessedEventServiceImpl implements EventTrackerService {
    private final ProcessedEventRepository repository;

    public void create(String loanRequestEventId) {
        repository.save(new ProcessedEvent(loanRequestEventId));
    }

    public Optional<ProcessedEvent> get(String loanRequestEventId) {
        return repository.findById(loanRequestEventId);
    }
}
