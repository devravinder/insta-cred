package com.paravar.instacred.instacred.loanHub.domain.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateLoanType(
        @NotNull(message = "Name cannot be null") String name,
        @NotNull(message = "Category cannot be null") LoanCategoryEnum category,
        @NotNull @Min(value = 1, message = "Interest rate must be greater than 0") double interestRate,
        @NotNull @Min(value = 1, message = "Max loan amount must be greater than 0") double maxLoanAmount) {}
;
