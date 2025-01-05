package com.paravar.instacred.creditScore.domain.models;

import jakarta.validation.constraints.NotNull;

public record CreateCreditScoreRequest(
        @NotNull(message = "PanNo should not be null") String panNo,
        @NotNull(message = "UserName should not be null") String userName) {}
