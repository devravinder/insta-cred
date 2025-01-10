package com.paravar.instacred.loanProcess;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "loan-process")
public record ApplicationProperties(
        @NotNull String loanRequestsExchange,
        @NotNull String newLoanRequestsQueue,
        @NotNull String approvedLoanRequestsRoutingKey,
        @NotNull String rejectedLoanRequestsRoutingKey) {}
