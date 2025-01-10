package com.paravar.instacred.loanHub.domain;

import com.paravar.instacred.loanHub.domain.models.LoanRequest;

public interface LoanRequestEventCreator {
    void create(Long id, LoanRequest request);
}
