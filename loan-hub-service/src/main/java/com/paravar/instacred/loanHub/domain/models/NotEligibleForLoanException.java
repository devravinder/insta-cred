package com.paravar.instacred.loanHub.domain.models;

public class NotEligibleForLoanException extends RuntimeException {
    public NotEligibleForLoanException(String message) {
        super(message);
    }

    public static NotEligibleForLoanException of(String panNo) {
        return new NotEligibleForLoanException("PAN no:" + panNo + " is not eligible for loan");
    }
}
