package com.paravar.instacred.loanHub;

import jakarta.validation.constraints.Min;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "loan-hub")
public record ApplicationProperties(@DefaultValue("10") @Min(1) int pageSize) {}
