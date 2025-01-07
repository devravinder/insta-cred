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
                .get(loanRequestEvent.getId())
                .map(event -> {
                    log.info("Loan request event {} already processed", loanRequestEvent);
                    return null;
                })
                .or(() -> {
                    log.info("Loan request event {} not yet processed", loanRequestEvent);
                    eventTrackerService.create(loanRequestEvent.getId());
                    return Optional.empty();
                });
    }
}
