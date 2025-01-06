package com.paravar.instacred.loanHub.domain.models;

public class LoanRequestNotFoundException extends RuntimeException {
    public LoanRequestNotFoundException(String message) {
        super(message);
    }

    public static LoanRequestNotFoundException of(Long id) {
        return new LoanRequestNotFoundException("Loan request with id " + id + " not found");
    }
}
