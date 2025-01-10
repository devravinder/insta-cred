package com.paravar.instacred.loanProcess.domain.loanProcess;

import com.paravar.instacred.common.jpa.models.LoanRequestEvent;
import com.paravar.instacred.common.messageQueue.MessagePublisher;
import com.paravar.instacred.common.messageQueue.QueueMessage;
import com.paravar.instacred.loanProcess.ApplicationProperties;
import com.paravar.instacred.loanProcess.domain.NewLoanRequestHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
class NewLoanRequestHandlerImpl implements NewLoanRequestHandler {
    private final ApplicationProperties properties;
    private final MessageMapper messageMapper;
    private final MessagePublisher messagePublisher;

    public void processLoan(LoanRequestEvent event) {

        // do something ( keep in different service )
        if (event.getLoanRequestId() % 5 == 0) {
            QueueMessage message = messageMapper.canceledLoanRequest(event);
            this.messagePublisher.publish(message);
        } else {
            QueueMessage message = messageMapper.approvedLoanRequest(event);
            this.messagePublisher.publish(message);
        }
    }
}
