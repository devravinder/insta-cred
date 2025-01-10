package com.paravar.instacred.creditScore.domain;

import com.paravar.instacred.common.domain.models.CreditScore;
import com.paravar.instacred.creditScore.domain.models.CreateCreditScoreRequest;
import com.paravar.instacred.creditScore.domain.models.UpdateCreditScoreRequest;

public interface CreditScoreService {
    CreditScore create(CreateCreditScoreRequest creditScore);

    CreditScore findByPanNo(String panNo);

    void onLoanApproval(UpdateCreditScoreRequest request);
}
