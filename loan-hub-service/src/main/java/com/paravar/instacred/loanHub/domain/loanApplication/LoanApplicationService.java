package com.paravar.instacred.loanHub.domain.loanApplication;

import com.paravar.instacred.loanHub.domain.models.CreateLoanApplicationRequest;
import com.paravar.instacred.loanHub.domain.models.LoanApplication;
import com.paravar.instacred.loanHub.domain.models.LoanApplicationNotFoundException;
import com.paravar.instacred.loanHub.domain.models.LoanApplicationStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LoanApplicationService {
    private final LoanApplicationRepository repository;
    private final LoanApplicationMapper mapper;
    private final LoanApplicationValidator validator;



    // we are not handling all cases of the real world application
    public LoanApplication create(CreateLoanApplicationRequest request) {

        validator.validate(request);

        var entity = mapper.toEntity(request);
        entity.setStatus(LoanApplicationStatus.PENDING);

        entity = repository.save(entity);
        return mapper.map(entity);
    }

    public LoanApplication getLoanApplication(Long id) {
        return repository.findById(id).map(mapper::map).orElseThrow(() -> LoanApplicationNotFoundException.of(id));
    }


}
