package com.paravar.instacred.loanProcess.domain;

import com.paravar.instacred.common.jpa.models.LoanRequestEvent;

public interface NewLoanRequestHandler {
    void processLoan(LoanRequestEvent loanRequestEvent);
}
