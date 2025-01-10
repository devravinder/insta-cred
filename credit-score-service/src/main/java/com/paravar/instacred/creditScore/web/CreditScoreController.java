package com.paravar.instacred.creditScore.web;

import static com.paravar.instacred.common.domain.AppConstants.NOT_FOUND_TYPE;
import static com.paravar.instacred.common.domain.AppConstants.SERVICE_NAME;

import com.paravar.instacred.common.domain.models.CreditScore;
import com.paravar.instacred.common.domain.models.CreditScoreNotFoundException;
import com.paravar.instacred.creditScore.domain.CreditScoreService;
import com.paravar.instacred.creditScore.domain.models.CreateCreditScoreRequest;
import jakarta.validation.Valid;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/creditScores")
@RequiredArgsConstructor
@Slf4j
class CreditScoreController {
    private final CreditScoreService creditScoreService;

    @PostMapping
    public CreditScore create(@RequestBody @Valid CreateCreditScoreRequest creditScore) {
        return creditScoreService.create(creditScore);
    }

    @GetMapping("/{panNo}")
    public CreditScore findByPanNo(@PathVariable String panNo) {
        log.info("Fetching credit score for panNo: {}", panNo);
        return creditScoreService.findByPanNo(panNo);
    }

    @ExceptionHandler(CreditScoreNotFoundException.class)
    public ProblemDetail handleCreditScoreNotFoundException(CreditScoreNotFoundException ex) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Credit Score Not Found");
        problemDetail.setType(NOT_FOUND_TYPE);
        problemDetail.setProperty("service", SERVICE_NAME);
        problemDetail.setProperty("error_category", "Not Found");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
}
