package com.paravar.instacred.creditScore.domain.models;

public class CreditScoreNotFoundException extends RuntimeException {
    public CreditScoreNotFoundException(String message) {
        super(message);
    }

    public static CreditScoreNotFoundException of(String panNo) {
        return new CreditScoreNotFoundException("Credit score not found for the PanNo:" + panNo);
    }
}
