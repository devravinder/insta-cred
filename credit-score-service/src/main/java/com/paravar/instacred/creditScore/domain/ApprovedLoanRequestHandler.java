package com.paravar.instacred.creditScore.domain;

import com.paravar.instacred.creditScore.domain.models.LoanRequestEvent;

public interface ApprovedLoanRequestHandler {
    void handleApprovedLoanRequestEvent(LoanRequestEvent event);
}
