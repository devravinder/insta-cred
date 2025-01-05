package com.paravar.instacred.loanHub.web;

import static com.paravar.instacred.common.domain.AppConstants.*;

import com.paravar.instacred.common.jpa.models.PagedResult;
import com.paravar.instacred.loanHub.domain.LoanTypeService;
import com.paravar.instacred.loanHub.domain.models.CreateLoanType;
import com.paravar.instacred.loanHub.domain.models.LoanType;
import com.paravar.instacred.loanHub.domain.models.LoanTypeNotFoundException;
import jakarta.validation.Valid;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loanTypes")
@Slf4j
@AllArgsConstructor
public class LoanTypeController {

    private final LoanTypeService loanTypeService;

    @GetMapping
    public PagedResult<LoanType> getAllLoanTypes(@RequestParam(name = "page", defaultValue = "1") int pageNo) {
        log.info("Getting all loan types for page {}", pageNo);
        return loanTypeService.getLoanTypes(pageNo);
    }

    @GetMapping("/{id}")
    public LoanType getLoanType(@PathVariable Long id) {
        return loanTypeService.getLoanType(id);
    }

    @PostMapping
    public LoanType createLoanType(@Valid CreateLoanType loanType) {
        return loanTypeService.createLoanType(loanType);
    }

    @PutMapping("/{id}")
    public LoanType updateLoanType(@PathVariable Long id, @Valid LoanType loanType) {
        return loanTypeService.updateLoanType(id, loanType);
    }

    @DeleteMapping("/{id}")
    public void deleteLoanType(@PathVariable Long id) {
        loanTypeService.deleteLoanType(id);
    }

    @ExceptionHandler(LoanTypeNotFoundException.class)
    public ProblemDetail handleLoanTypeNotFoundException(LoanTypeNotFoundException ex) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Loan Type Not Found");
        problemDetail.setType(NOT_FOUND_TYPE);
        problemDetail.setProperty("service", SERVICE_NAME);
        problemDetail.setProperty("error_category", "Not Found");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
}
