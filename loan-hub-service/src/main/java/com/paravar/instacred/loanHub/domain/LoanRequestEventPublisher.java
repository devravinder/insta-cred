package com.paravar.instacred.loanHub.domain;

import com.paravar.instacred.loanHub.domain.models.LoanRequestEvent;

public interface LoanRequestEventPublisher {
    void publish(LoanRequestEvent event);

}
