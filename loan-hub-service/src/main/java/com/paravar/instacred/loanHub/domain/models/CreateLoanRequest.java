package com.paravar.instacred.loanHub.domain.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateLoanRequest(
        @NotNull(message = "Applicant name cannot be null") String applicantName,
        @NotNull(message = "PAN number cannot be null") String panNo,
        @NotNull(message = "Email cannot be null") String email,
        @NotNull(message = "Loan type id cannot be null") Long loanTypeId,
        @Min(value = 1, message = "Loan amount must be greater than 1") double loanAmount) {}
