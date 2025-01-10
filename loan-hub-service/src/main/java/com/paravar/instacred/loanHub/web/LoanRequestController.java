package com.paravar.instacred.loanHub.web;

import static com.paravar.instacred.common.domain.AppConstants.*;

import com.paravar.instacred.common.domain.models.CreditScoreNotFoundException;
import com.paravar.instacred.loanHub.domain.LoanRequestService;
import com.paravar.instacred.loanHub.domain.models.*;
import jakarta.validation.Valid;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loanRequests")
@RequiredArgsConstructor
@Slf4j
class LoanRequestController {
    private final LoanRequestService loanRequestService;

    @GetMapping("/{id}")
    public LoanRequest getLoanRequest(@PathVariable Long id) {
        return loanRequestService.getLoanRequest(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoanRequest createLoanRequest(@RequestBody @Valid CreateLoanRequest loanApplication) {
        log.info("Received Loan Application: {}", loanApplication.panNo());
        return loanRequestService.create(loanApplication);
    }

    @ExceptionHandler(LoanRequestNotFoundException.class)
    public ProblemDetail handleLoanRequestNotFoundException(LoanRequestNotFoundException ex) {

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
