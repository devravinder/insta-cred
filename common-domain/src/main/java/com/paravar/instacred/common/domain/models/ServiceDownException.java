package com.paravar.instacred.common.domain.models;

public class ServiceDownException extends RuntimeException {
    public ServiceDownException(String message) {
        super(message);
    }

    public static ServiceDownException of(String serviceName) {
        return new ServiceDownException("Service:" + serviceName + " is down");
    }
}
