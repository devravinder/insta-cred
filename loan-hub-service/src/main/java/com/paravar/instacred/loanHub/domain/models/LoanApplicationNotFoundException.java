package com.paravar.instacred.loanHub.domain.models;

public class LoanApplicationNotFoundException extends RuntimeException {
    public LoanApplicationNotFoundException(String message) {
        super(message);
    }

    public static LoanApplicationNotFoundException of(Long id) {
        return new LoanApplicationNotFoundException("Loan application with id " + id + " not found");
    }
}
