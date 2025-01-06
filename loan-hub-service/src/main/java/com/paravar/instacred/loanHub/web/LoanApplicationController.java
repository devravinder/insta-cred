package com.paravar.instacred.loanHub.web;

import com.paravar.instacred.common.domain.models.CreditScoreNotFoundException;
import com.paravar.instacred.loanHub.domain.loanRequest.LoanRequestService;
import com.paravar.instacred.loanHub.domain.models.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

import static com.paravar.instacred.common.domain.AppConstants.*;

@RestController
@RequestMapping("/api/loanApplications")
@RequiredArgsConstructor
@Slf4j
public class LoanApplicationController {
    private final LoanRequestService loanApplicationService;

    @GetMapping("/{id}")
    public LoanRequest getLoanApplication(@PathVariable Long id) {
        return loanApplicationService.getLoanApplication(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoanRequest createLoanApplication(@RequestBody @Valid CreateLoanApplicationRequest loanApplication) {
        return loanApplicationService.create(loanApplication);
    }

    @ExceptionHandler(LoanRequestNotFoundException.class)
    public ProblemDetail handleLoanApplicationNotFoundException(LoanRequestNotFoundException ex) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Loan Application Not Found");
        problemDetail.setType(NOT_FOUND_TYPE);
        problemDetail.setProperty("service", SERVICE_NAME);
        problemDetail.setProperty("error_category", "Not Found");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(CreditScoreNotFoundException.class)
    public ProblemDetail creditScoreNotFoundException(CreditScoreNotFoundException ex) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setTitle("Invalid PAN Number");
        problemDetail.setType(BAD_REQUEST_TYPE);
        problemDetail.setProperty("service", SERVICE_NAME);
        problemDetail.setProperty("error_category", "Invalid");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
    @ExceptionHandler(NotEligibleForLoanException.class)
    public ProblemDetail notEligibleForLoan(NotEligibleForLoanException ex) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setTitle("PAN No is not eligible for loan");
        problemDetail.setType(BAD_REQUEST_TYPE);
        problemDetail.setProperty("service", SERVICE_NAME);
        problemDetail.setProperty("error_category", "Invalid");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
    @ExceptionHandler(LoanTypeNotFoundException.class)
    public ProblemDetail invalidLoanType(LoanTypeNotFoundException ex) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setTitle("Applying for invalid loan type");
        problemDetail.setType(BAD_REQUEST_TYPE);
        problemDetail.setProperty("service", SERVICE_NAME);
        problemDetail.setProperty("error_category", "Invalid");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
}
