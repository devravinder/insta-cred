package com.paravar.instacred.loanHub.domain.loanRequestEvent;

import com.paravar.instacred.loanHub.ApplicationProperties;
import com.paravar.instacred.loanHub.domain.LoanRequestEventPublisher;
import com.paravar.instacred.loanHub.domain.LoanRequestEventService;
import com.paravar.instacred.loanHub.domain.models.LoanRequestEvent;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
class LoanRequestEventServiceImpl implements LoanRequestEventService {
    private final ApplicationProperties properties;
    private final LoanRequestEventRepository repository;
    private final LoanRequestEventPublisher loanRequestEventPublisher;

    public void create(Long loanRequestId) {
        log.info("saving loan request event");
        LoanRequestEvent event = new LoanRequestEvent(UUID.randomUUID().toString(),loanRequestId, LocalDateTime.now());
        // if outbox pattern is enabled, save the event in db ...else directly publish
        if (properties.outBoxPatternEnabled()){
            repository.save(event);
        }else {
            this.publishEvent(event);
        }
    }

    public void delete(String id) {
        log.info("deleting loan request event entity");
        repository.deleteById(id);
    }

    public void publishLoanRequestEvents() {
        Sort sort = Sort.by("createdAt").ascending();
        Pageable pageable = PageRequest.of(0, properties.publishNewLoanRequestsPerJob(), sort);  // Limit to the first 10 records

        List<LoanRequestEvent> events = repository.findAll(pageable).getContent();
        log.info("Found {} Order Events to be published", events.size());
        for (LoanRequestEvent event : events) {
            this.publishEvent(event);
            repository.deleteById(event.getId());
        }
    }

    public void publishEvent(LoanRequestEvent event) {
        loanRequestEventPublisher.publish(event);
    }
}
