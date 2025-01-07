package com.paravar.instacred.loanHub.domain.events;

import com.paravar.instacred.loanHub.ApplicationProperties;
import com.paravar.instacred.loanHub.domain.LoanRequestEventPublisher;
import com.paravar.instacred.loanHub.domain.models.LoanRequestEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
class LoanRequestEventPublisherImpl implements LoanRequestEventPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final ApplicationProperties properties;

    public void publish(LoanRequestEvent event) {
        this.send(properties.newLoanRequestsQueue(), event);
    }

    private void send(String routingKey, Object payload) {
        rabbitTemplate.convertAndSend(properties.loanRequestsExchange(), routingKey, payload);
    }
}
