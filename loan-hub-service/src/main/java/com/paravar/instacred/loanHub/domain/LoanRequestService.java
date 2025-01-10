package com.paravar.instacred.loanHub.domain;

import com.paravar.instacred.loanHub.domain.models.CreateLoanRequest;
import com.paravar.instacred.loanHub.domain.models.LoanRequest;
import com.paravar.instacred.loanHub.domain.models.LoanRequestStatus;

public interface LoanRequestService {
    LoanRequest create(CreateLoanRequest request);

    LoanRequest getLoanRequest(Long id);

    void updateLoanRequest(Long id, LoanRequestStatus status);
}
