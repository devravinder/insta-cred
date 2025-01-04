package com.paravar.instacred.instacred.loanHub.domain.models;

public class LoanTypeNotFoundException extends RuntimeException {
    public LoanTypeNotFoundException(String message) {
        super(message);
    }

    public static LoanTypeNotFoundException of(Long id) {
        return new LoanTypeNotFoundException("Loan type with id " + id + " not found");
    }
}
