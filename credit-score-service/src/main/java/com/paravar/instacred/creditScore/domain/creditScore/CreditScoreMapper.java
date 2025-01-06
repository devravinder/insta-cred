package com.paravar.instacred.creditScore.domain.creditScore;

import com.paravar.instacred.common.domain.models.CreditScore;
import com.paravar.instacred.creditScore.domain.models.CreateCreditScoreRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
interface CreditScoreMapper {

    CreditScore map(CreditScoreEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "score", ignore = true)
    @Mapping(target = "noOfLoans", ignore = true)
    @Mapping(target = "totalLoanAmount", ignore = true)
    CreditScoreEntity toEntity(CreateCreditScoreRequest creditScore);
}
