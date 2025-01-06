package com.paravar.instacred.loanHub.domain.loanApplication;

import com.paravar.instacred.loanHub.domain.models.CreateLoanApplicationRequest;
import com.paravar.instacred.loanHub.domain.models.LoanApplication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
interface LoanApplicationMapper {
    LoanApplication map(LoanApplicationEntity entity);

    @Mapping(target = "id", ignore = true)
    LoanApplicationEntity toEntity(LoanApplication loanApplication);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    LoanApplicationEntity toEntity(CreateLoanApplicationRequest request);
}
