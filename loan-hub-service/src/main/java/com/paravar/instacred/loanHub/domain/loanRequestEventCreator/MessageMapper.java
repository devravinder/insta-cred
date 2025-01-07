package com.paravar.instacred.loanHub.domain.loanRequestEventCreator;

import com.paravar.instacred.common.jpa.models.LoanRequestEvent;
import com.paravar.instacred.common.messageQueue.QueueMessage;
import com.paravar.instacred.loanHub.ApplicationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class MessageMapper {

    private final ApplicationProperties properties;

    QueueMessage newLoanRequest(LoanRequestEvent event) {
        return new QueueMessage(properties.loanRequestsExchange(), properties.newLoanRequestsQueue(), event);
    }
}
