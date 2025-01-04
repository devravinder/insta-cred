package com.paravar.instacred.loanHub.domain;

import com.paravar.instacred.common.models.PagedResult;
import com.paravar.instacred.loanHub.domain.models.CreateLoanType;
import com.paravar.instacred.loanHub.domain.models.LoanType;

public interface LoanTypeService {
    PagedResult<LoanType> getLoanTypes(int pageNo);
    LoanType getLoanType(Long id);
    LoanType createLoanType(CreateLoanType loanType);
    LoanType updateLoanType(Long id, LoanType loanType);
    void deleteLoanType(Long id);
}
