package com.paravar.instacred.instacred.loanHub.domain.loanTypes;

import com.paravar.instacred.instacred.loanHub.domain.models.CreateLoanType;
import com.paravar.instacred.instacred.loanHub.domain.models.LoanType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
interface LoanTypeMapper {
    LoanType map(LoanTypeEntity entity);

    LoanTypeEntity toEntity(LoanType entity);

    @Mapping(target = "id", ignore = true)
    LoanTypeEntity toEntity(CreateLoanType createLoanType);
}
