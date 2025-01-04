package com.paravar.instacred.loanHub.domain.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record LoanType(
        @NotNull(message = "Id cannot be null") Long id,
        @NotNull(message = "Name cannot be null") String name,
        @NotNull(message = "Category cannot be null") LoanCategoryEnum category,
        @NotNull @Min(value = 1, message = "Interest rate must be greater than 0") double interestRate,
        @NotNull @Min(value = 1, message = "Max loan amount must be greater than 0") double maxLoanAmount) implements Serializable {}
;
