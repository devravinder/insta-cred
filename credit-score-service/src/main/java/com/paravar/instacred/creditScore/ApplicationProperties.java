package com.paravar.instacred.creditScore;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "credit-score")
public record ApplicationProperties(@NotNull String loanRequestsExchange, @NotNull String approvedLoanRequestsQueue) {}
