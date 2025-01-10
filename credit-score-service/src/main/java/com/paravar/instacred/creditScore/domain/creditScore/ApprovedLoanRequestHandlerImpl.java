package com.paravar.instacred.creditScore.domain.creditScore;

import com.paravar.instacred.creditScore.domain.ApprovedLoanRequestHandler;
import com.paravar.instacred.creditScore.domain.CreditScoreService;
import com.paravar.instacred.creditScore.domain.models.LoanRequestEvent;
import com.paravar.instacred.creditScore.domain.models.UpdateCreditScoreRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
class ApprovedLoanRequestHandlerImpl implements ApprovedLoanRequestHandler {

    private final CreditScoreService creditScoreService;

    @Override
    public void handleApprovedLoanRequestEvent(LoanRequestEvent event) {
        creditScoreService.onLoanApproval(new UpdateCreditScoreRequest(event.getPanNo(), event.getLoanAmount()));
    }
}
