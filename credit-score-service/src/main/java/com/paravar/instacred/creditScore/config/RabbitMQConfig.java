package com.paravar.instacred.creditScore.config;

import com.paravar.instacred.creditScore.ApplicationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
class RabbitMQConfig {
    private final ApplicationProperties properties;

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(properties.loanRequestsExchange());
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Queue approvedLoanRequestsQueue() {
        return new Queue(properties.approvedLoanRequestsQueue());
    }

    @Bean
    public Binding approvedLoanRequestsQueuebinding() {
        return BindingBuilder.bind(approvedLoanRequestsQueue())
                .to(exchange())
                .with(properties.approvedLoanRequestsQueue()); // we are using the queue name as the routing key
    }
}
