package com.paravar.instacred.creditScore.domain.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UpdateCreditScoreRequest(
        @NotNull(message = "PanNo should not be null") String panNo,
        @Min(value = 0, message = "LoanAmount should be greater than 0") double loanAmount) {}
;
