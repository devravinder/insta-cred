package com.paravar.instacred.creditScore.domain.models;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoanRequestEvent {
    private String eventId;
    private Long loanRequestId;
    private String panNo;
    private double loanAmount;
    private LocalDateTime createdAt = LocalDateTime.now();
}
