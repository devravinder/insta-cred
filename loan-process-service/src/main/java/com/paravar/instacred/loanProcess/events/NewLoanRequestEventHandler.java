package com.paravar.instacred.loanProcess.events;

import com.paravar.instacred.common.jpa.EventTrackerService;
import com.paravar.instacred.common.jpa.models.LoanRequestEvent;
import com.paravar.instacred.loanProcess.ApplicationProperties;
import com.paravar.instacred.loanProcess.domain.NewLoanRequestHandler;
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
class NewLoanRequestEventHandler {

    private final ApplicationProperties properties;
    private final EventTrackerService eventTrackerService;
    private final NewLoanRequestHandler newLoanRequestHandler;

    @RabbitListener(queues = "${loan-process.new-loan-requests-queue}")
    public void handleNewLoanRequestEvent(LoanRequestEvent loanRequestEvent) {

        /*
         we don't get any information about queue ( like queue name & other details ).
         If we want that information, we have to implement ChannelAwareMessageListener
         ref: https://docs.spring.io/spring-amqp/reference/amqp/receiving-messages/async-consumer.html?utm_source=chatgpt.com
        */

        eventTrackerService
                .get(loanRequestEvent.getEventId(), properties.newLoanRequestsQueue())
                .map(event -> {
                    log.info("Already processed for Loan request event: {} ", event.getEventId());
                    return null;
                })
                .or(() -> {
                    log.info("Loan request with id: {} is being processed", loanRequestEvent.getLoanRequestId());
                    newLoanRequestHandler.processLoan(loanRequestEvent);
                    eventTrackerService.logEvent(
                            loanRequestEvent.getEventId(),
                            properties.newLoanRequestsQueue(),
                            loanRequestEvent.getLoanRequestId());
                    return Optional.empty();
                });
    }
}
