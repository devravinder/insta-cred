package com.paravar.instacred.loanHub.domain.loanRequestEventCreator;

import com.paravar.instacred.common.jpa.models.LoanRequestEvent;
import com.paravar.instacred.loanHub.domain.LoanRequestEventCreator;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
@ConditionalOnProperty(name = "loan-hub.out-box-pattern-enabled", havingValue = "false")
class DirectLoanRequestEventCreatorImpl implements LoanRequestEventCreator {
    private final LoanRequestEventPublisher loanRequestEventPublisher;

    @Override
    public void create(Long loanRequestId) {
        LoanRequestEvent event = new LoanRequestEvent(UUID.randomUUID().toString(), loanRequestId, LocalDateTime.now());
        loanRequestEventPublisher.publish(event);
    }
}
