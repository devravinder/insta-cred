package com.paravar.instacred.creditScore.events;

import com.paravar.instacred.creditScore.ApplicationProperties;
import com.paravar.instacred.creditScore.domain.ApprovedLoanRequestHandler;
import com.paravar.instacred.creditScore.domain.EventTrackerService;
import com.paravar.instacred.creditScore.domain.models.LoanRequestEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
class ApprovedLoanEventHandler {

    private final ApplicationProperties properties;
    private final EventTrackerService eventTrackerService;
    private final ApprovedLoanRequestHandler approvedLoanEventHandler;

    @RabbitListener(queues = "${credit-score.approved-loan-requests-queue}")
    public void handleApprovedLoanRequestEvent(LoanRequestEvent event) {

        if (eventTrackerService.isEventProcessed(event.getEventId())) {
            log.info("Already processed for the Approved Loan request event: {} ", event.getEventId());

        } else {
            log.info("Approved Loan request with id: {} is being processed", event.getLoanRequestId());
            approvedLoanEventHandler.handleApprovedLoanRequestEvent(event);
            eventTrackerService.logEvent(
                    event.getEventId(), properties.approvedLoanRequestsQueue(), event.getLoanRequestId());
        }
    }
}
