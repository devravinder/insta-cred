package com.paravar.instacred.common.messageQueue;

public record QueueMessage(String exchange, String routingKey, Object payload) {}
