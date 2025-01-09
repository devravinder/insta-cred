package com.paravar.instacred.loanProcess.events;

import com.paravar.instacred.common.jpa.models.LoanRequestEvent;
import com.paravar.instacred.loanProcess.domain.EventTrackerService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
class LoanRequestEventHandler {

    private final EventTrackerService eventTrackerService;

    @RabbitListener(queues = "${loan-process.new-loan-requests-queue}")
    public void handleNewLoanRequestEvent(LoanRequestEvent loanRequestEvent) {

        eventTrackerService
                .get(loanRequestEvent.getEventId())
                .map(event -> {
                    log.info("Already processed for Loan request event: {} ", event.eventId);
                    return null;
                })
                .or(() -> {
                    log.info("Loan request with id: {} is being processed", loanRequestEvent.getLoanRequestId());
                    eventTrackerService.create(loanRequestEvent.getEventId(), loanRequestEvent.getLoanRequestId());
                    return Optional.empty();
                });
    }
}
