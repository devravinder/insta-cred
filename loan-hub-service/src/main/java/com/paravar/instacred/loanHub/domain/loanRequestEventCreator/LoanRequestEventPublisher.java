package com.paravar.instacred.loanHub.domain.loanRequestEventCreator;

import com.paravar.instacred.common.jpa.models.LoanRequestEvent;
import com.paravar.instacred.common.messageQueue.MessagePublisher;
import com.paravar.instacred.common.messageQueue.QueueMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
class LoanRequestEventPublisher {
    private final MessageMapper mapper;
    private final MessagePublisher publisher;

    public void publish(LoanRequestEvent event) {
        QueueMessage message = mapper.newLoanRequest(event);
        publisher.publish(message);
    }
}
