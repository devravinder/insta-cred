package com.paravar.instacred.loanHub;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "loan-hub")
public record ApplicationProperties(
        @DefaultValue("10") @Min(1) int pageSize,
        @NotNull String credScoreServiceUrl,
        @NotNull String loanRequestsExchange,
        @NotNull String newLoanRequestsQueue,
        @NotNull String publishNewLoanRequestsJobCron,
        @NotNull @DefaultValue("5") int publishNewLoanRequestsPerJob,
        @NotNull Boolean outBoxPatternEnabled) {}
