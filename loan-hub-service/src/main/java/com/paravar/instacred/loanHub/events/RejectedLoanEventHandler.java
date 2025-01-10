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

@Service
@RequiredArgsConstructor
@Slf4j
class RejectedLoanEventHandler {
    private final ApplicationProperties properties;
    private final EventTrackerService eventTrackerService;
    private final LoanRequestService loanRequestService;

    @RabbitListener(queues = "${loan-hub.rejected-loan-requests-queue}")
    public void handleApprovedLoanEvent(LoanRequestEvent loanRequestEvent) {

        eventTrackerService
                .get(loanRequestEvent.getEventId(), properties.rejectedLoanRequestsQueue())
                .map(event -> {
                    log.info("Already processed for the Cancelled Loan request event: {} ", event.getEventId());
                    return null;
                })
                .or(() -> {
                    log.info(
                            "handling the cancelled loan request with id: {} is being processed",
                            loanRequestEvent.getLoanRequestId());
                    loanRequestService.updateLoanRequest(
                            loanRequestEvent.getLoanRequestId(), LoanRequestStatus.REJECTED);
                    eventTrackerService.logEvent(
                            loanRequestEvent.getEventId(),
                            properties.rejectedLoanRequestsQueue(),
                            loanRequestEvent.getLoanRequestId());
                    return Optional.empty();
                });
    }
}
