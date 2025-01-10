package com.paravar.instacred.loanProcess.domain.loanProcess;

import com.paravar.instacred.common.jpa.models.LoanRequestEvent;
import com.paravar.instacred.common.messageQueue.QueueMessage;
import com.paravar.instacred.loanProcess.ApplicationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class MessageMapper {
    private final ApplicationProperties properties;

    QueueMessage approvedLoanRequest(LoanRequestEvent event) {
        return new QueueMessage(properties.loanRequestsExchange(), properties.approvedLoanRequestsRoutingKey(), event);
    }

    QueueMessage canceledLoanRequest(LoanRequestEvent event) {
        return new QueueMessage(properties.loanRequestsExchange(), properties.rejectedLoanRequestsRoutingKey(), event);
    }
}
