package com.paravar.instacred.common.messageQueue;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Slf4j
@Service
class MessagePublisherImpl implements MessagePublisher {
    private final RabbitTemplate rabbitTemplate;
    /*
     to use this service, we need to create RabbitTemplate bean in the application context
    */

    @Override
    public void publish(QueueMessage message) {
        rabbitTemplate.convertAndSend(message.exchange(), message.routingKey(), message.payload());
    }
}
