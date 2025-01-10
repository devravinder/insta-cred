package com.paravar.instacred.loanHub.domain.loanRequest;

import com.paravar.instacred.common.domain.models.CreditScore;
import com.paravar.instacred.loanHub.clients.web.creditScore.CreditScoreService;
import com.paravar.instacred.loanHub.domain.models.CreateLoanRequest;
import com.paravar.instacred.loanHub.domain.models.LoanTypeNotFoundException;
import com.paravar.instacred.loanHub.domain.models.NotEligibleForLoanException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class LoanRequestValidator {

    private final CreditScoreService creditScoreService;
    private final LoanRequestRepository repository;

    private static final int ELIGIBLE_MIN_SCORE = 500;

    public void validate(CreateLoanRequest request) {
        if (!isEligible(request)) {
            throw NotEligibleForLoanException.of(request.panNo());
        }
        // do other validations ( we are not doing all )
        if (!repository.existsById(request.loanTypeId())) {
            throw LoanTypeNotFoundException.of(request.loanTypeId());
        }
    }

    private boolean isEligible(CreateLoanRequest application) {
        CreditScore creditScore = creditScoreService.getCreditScore(application.panNo());
        log.info("Credit score for panNo {} is {}", application.panNo(), creditScore.score());
        return creditScore.score() >= ELIGIBLE_MIN_SCORE;
    }
}
