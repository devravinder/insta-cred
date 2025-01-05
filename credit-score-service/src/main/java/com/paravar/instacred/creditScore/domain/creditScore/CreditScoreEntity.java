package com.paravar.instacred.creditScore.domain.creditScore;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Builder
@Document(collection = "credit_scores")
public class CreditScoreEntity {
    @Id
    private String id;

    private int score;
    private String panNo;
    private String userName;
    private int noOfLoans;
    private double totalLoanAmount;
}
