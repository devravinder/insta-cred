package com.paravar.instacred.loanHub.domain.loanRequest;

import com.paravar.instacred.common.domain.models.CreditScore;
import com.paravar.instacred.loanHub.clients.creditScore.CreditScoreService;
import com.paravar.instacred.loanHub.domain.models.CreateLoanApplicationRequest;
import com.paravar.instacred.loanHub.domain.models.LoanTypeNotFoundException;
import com.paravar.instacred.loanHub.domain.models.NotEligibleForLoanException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class LoanRequestValidator {

    private final CreditScoreService creditScoreService;
    private final LoanRequestRepository repository;

    private static final int ELIGIBLE_MIN_SCORE = 500;

    public void validate(CreateLoanApplicationRequest request) {
        if (!isEligible(request)) {
            throw NotEligibleForLoanException.of(request.panNo());
        }
        // do other validations ( we are not doing all )
        if(!repository.existsById(request.loanTypeId())){
            throw LoanTypeNotFoundException.of(request.loanTypeId());
        }
    }

    private boolean isEligible(CreateLoanApplicationRequest application) {
        CreditScore creditScore= creditScoreService.getCreditScore(application.panNo());
        return creditScore.score() >= ELIGIBLE_MIN_SCORE;
    }
}
