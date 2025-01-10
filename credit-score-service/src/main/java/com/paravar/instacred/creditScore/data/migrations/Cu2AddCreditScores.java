package com.paravar.instacred.creditScore.data.migrations;

import com.paravar.instacred.creditScore.domain.creditScore.CreditScoreEntity;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;

@ChangeUnit(id = "add-credit-scores", order = "002", author = "Ravinder Reddy Kothabad")
@RequiredArgsConstructor
public class Cu2AddCreditScores {

    private final MongoTemplate mongoTemplate;

    @Execution
    public void addCreditScores() {
        /*
        B.P:
          - we should use static data, bcz once after execution, it should not change
             - but we are using Entities/Object...which can be changed ( not recommended )
        */

        var creditScores = List.of(
                CreditScoreEntity.builder()
                        .panNo("1234567890")
                        .score(900)
                        .userName("Paravar")
                        .totalLoanAmount(10000.0)
                        .noOfLoans(2)
                        .build(),
                CreditScoreEntity.builder()
                        .panNo("1234567891")
                        .score(900)
                        .userName("Ravinder")
                        .totalLoanAmount(10000.0)
                        .noOfLoans(2)
                        .build(),
                CreditScoreEntity.builder()
                        .panNo("1234567892")
                        .score(500)
                        .userName("Reddy")
                        .totalLoanAmount(10000.0)
                        .noOfLoans(2)
                        .build(),
                CreditScoreEntity.builder()
                        .panNo("1234567893")
                        .score(500)
                        .userName("Kothabad")
                        .totalLoanAmount(10000.0)
                        .noOfLoans(2)
                        .build(),
                CreditScoreEntity.builder()
                        .panNo("1234567894")
                        .score(500)
                        .userName("Raj")
                        .totalLoanAmount(10000.0)
                        .noOfLoans(2)
                        .build(),
                CreditScoreEntity.builder()
                        .panNo("1234567895")
                        .score(500)
                        .userName("Technologies")
                        .totalLoanAmount(10000.0)
                        .noOfLoans(2)
                        .build(),
                CreditScoreEntity.builder()
                        .panNo("1234567896")
                        .score(500)
                        .userName("Insta Mart")
                        .totalLoanAmount(10000.0)
                        .noOfLoans(2)
                        .build(),
                CreditScoreEntity.builder()
                        .panNo("1234567897")
                        .score(500)
                        .userName("D Mart")
                        .totalLoanAmount(10000.0)
                        .noOfLoans(2)
                        .build(),
                CreditScoreEntity.builder()
                        .panNo("1234567898")
                        .score(500)
                        .userName("Jio")
                        .totalLoanAmount(10000.0)
                        .noOfLoans(2)
                        .build(),
                CreditScoreEntity.builder()
                        .panNo("1234567899")
                        .score(500)
                        .userName("ParavarTech")
                        .totalLoanAmount(10000.0)
                        .noOfLoans(2)
                        .build());

        mongoTemplate.insertAll(creditScores);
    }

    @RollbackExecution
    public void deleteCreditScores() {
        mongoTemplate.remove(CreditScoreEntity.class);
    }
}
