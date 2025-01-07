package com.paravar.instacred.loanHub.domain.loanRequestEventCreator;

import com.paravar.instacred.common.jpa.loanRequestEvent.LoanRequestEventRepository;
import com.paravar.instacred.common.jpa.models.LoanRequestEvent;
import com.paravar.instacred.loanHub.ApplicationProperties;
import com.paravar.instacred.loanHub.domain.LoanRequestEventCreator;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
@ConditionalOnProperty(name = "loan-hub.out-box-pattern-enabled", havingValue = "true", matchIfMissing = true)
class AbstractPatternLoanRequestEventCreatorImpl implements LoanRequestEventCreator {
    private final LoanRequestEventRepository repository;
    private final ApplicationProperties properties;
    private final LoanRequestEventPublisher loanRequestEventPublisher;

    @Override
    public void create(Long loanRequestId) {
        LoanRequestEvent event = new LoanRequestEvent(UUID.randomUUID().toString(), loanRequestId, LocalDateTime.now());
        repository.save(event);
    }

    public void publishLoanRequestEvents() {
        Sort sort = Sort.by("createdAt").ascending();
        Pageable pageable =
                PageRequest.of(0, properties.publishNewLoanRequestsPerJob(), sort); // Limit to the first 10 records

        List<LoanRequestEvent> events = repository.findAll(pageable).getContent();
        log.info("Found {} Order Events to be published", events.size());
        for (LoanRequestEvent event : events) {
            loanRequestEventPublisher.publish(event);
            repository.deleteById(event.getId());
        }
    }
}
