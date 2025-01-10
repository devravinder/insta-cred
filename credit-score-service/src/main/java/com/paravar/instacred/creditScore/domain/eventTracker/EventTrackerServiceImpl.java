package com.paravar.instacred.creditScore.domain.eventTracker;

import com.paravar.instacred.creditScore.domain.EventTrackerService;
import com.paravar.instacred.creditScore.domain.models.ProcessedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
class EventTrackerServiceImpl implements EventTrackerService {

    private final ProcessedEventRepository repository;

    @Override
    public void logEvent(String eventId, String eventName, Long objectId) {
        ProcessedEvent event = new ProcessedEvent(eventId, eventName, objectId);
        repository.save(event);
    }

    @Override
    public boolean isEventProcessed(String eventId) {
        return repository.existsById(eventId);
    }
}
