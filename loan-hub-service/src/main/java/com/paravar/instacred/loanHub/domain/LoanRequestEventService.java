package com.paravar.instacred.loanHub.domain;

public interface LoanRequestEventService {
    void create(Long loanRequestId);
    void publishLoanRequestEvents();

}
