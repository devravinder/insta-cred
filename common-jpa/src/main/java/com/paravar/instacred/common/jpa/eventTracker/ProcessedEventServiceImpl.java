package com.paravar.instacred.common.jpa.eventTracker;

import com.paravar.instacred.common.jpa.EventTrackerService;
import com.paravar.instacred.common.jpa.models.Event;
import com.paravar.instacred.common.jpa.models.ProcessedEvent;
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

    public void logEvent(String eventId, String eventName, Long objectId) {
        repository.save(new ProcessedEvent(eventId, eventName, objectId));
    }

    public Optional<Event> get(String eventId, String eventName) {
        return repository.findByEventId(eventId, eventName);
    }
}
