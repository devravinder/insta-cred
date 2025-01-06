package com.paravar.instacred.loanHub.domain.loanRequest;

import com.paravar.instacred.loanHub.domain.models.CreateLoanApplicationRequest;
import com.paravar.instacred.loanHub.domain.models.LoanRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
interface LoanRequestMapper {
    LoanRequest map(LoanRequestEntity entity);

    @Mapping(target = "id", ignore = true)
    LoanRequestEntity toEntity(LoanRequest loanApplication);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    LoanRequestEntity toEntity(CreateLoanApplicationRequest request);
}
