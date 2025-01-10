package com.paravar.instacred.creditScore.domain.creditScore;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Setter
@Getter
@Builder
@Document(collection = "credit_scores")
public class CreditScoreEntity {
    @Id
    private String id;

    private int score;

    @Field("pan_no")
    private String panNo;

    @Field("user_name")
    private String userName;

    @Field("no_of_loans")
    private int noOfLoans;

    @Field("total_loan_amount")
    private double totalLoanAmount;
}
