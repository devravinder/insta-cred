package com.paravar.instacred.loanHub.events;

import com.paravar.instacred.common.jpa.EventTrackerService;
import com.paravar.instacred.common.jpa.models.LoanRequestEvent;
import com.paravar.instacred.loanHub.ApplicationProperties;
import com.paravar.instacred.loanHub.domain.LoanRequestService;
import com.paravar.instacred.loanHub.domain.models.LoanRequestStatus;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
class ApprovedLoanEventHandler {
    private final ApplicationProperties properties;
    private final EventTrackerService eventTrackerService;
    private final LoanRequestService loanRequestService;

    @RabbitListener(queues = "${loan-hub.approved-loan-requests-queue}")
    public void handleApprovedLoanEvent(LoanRequestEvent loanRequestEvent) {

        eventTrackerService
                .get(loanRequestEvent.getEventId(), properties.approvedLoanRequestsQueue())
                .map(event -> {
                    log.info("Already processed for the Approved Loan event: {} ", event.getEventId());
                    return null;
                })
                .or(() -> {
                    log.info("Handling the approved loan request with id: {}", loanRequestEvent.getLoanRequestId());
                    loanRequestService.updateLoanRequest(
                            loanRequestEvent.getLoanRequestId(), LoanRequestStatus.APPROVED);
                    eventTrackerService.logEvent(
                            loanRequestEvent.getEventId(),
                            properties.approvedLoanRequestsQueue(),
                            loanRequestEvent.getLoanRequestId());
                    return Optional.empty();
                });
    }
}
