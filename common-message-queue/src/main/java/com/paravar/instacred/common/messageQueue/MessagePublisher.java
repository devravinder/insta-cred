package com.paravar.instacred.common.messageQueue;

public interface MessagePublisher {
    void publish(QueueMessage payload);
}
