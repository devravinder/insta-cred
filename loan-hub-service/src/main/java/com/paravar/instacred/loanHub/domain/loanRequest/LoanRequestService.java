package com.paravar.instacred.loanHub.domain.loanRequest;

import com.paravar.instacred.loanHub.domain.models.CreateLoanApplicationRequest;
import com.paravar.instacred.loanHub.domain.models.LoanRequest;
import com.paravar.instacred.loanHub.domain.models.LoanRequestNotFoundException;
import com.paravar.instacred.loanHub.domain.models.LoanRequestStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LoanRequestService {
    private final LoanRequestRepository repository;
    private final LoanRequestMapper mapper;
    private final LoanRequestValidator validator;



    // we are not handling all cases of the real world application
    public LoanRequest create(CreateLoanApplicationRequest request) {

        validator.validate(request);

        var entity = mapper.toEntity(request);
        entity.setStatus(LoanRequestStatus.PENDING);

        entity = repository.save(entity);
        return mapper.map(entity);
    }

    public LoanRequest getLoanApplication(Long id) {
        return repository.findById(id).map(mapper::map).orElseThrow(() -> LoanRequestNotFoundException.of(id));
    }


}
