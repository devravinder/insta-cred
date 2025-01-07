package com.paravar.instacred.loanHub.domain.loanRequest;

import com.paravar.instacred.loanHub.domain.LoanRequestEventCreator;
import com.paravar.instacred.loanHub.domain.LoanRequestService;
import com.paravar.instacred.loanHub.domain.models.CreateLoanRequest;
import com.paravar.instacred.loanHub.domain.models.LoanRequest;
import com.paravar.instacred.loanHub.domain.models.LoanRequestNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
class LoanRequestServiceImpl implements LoanRequestService {
    private final LoanRequestRepository repository;
    private final LoanRequestMapper mapper;
    private final LoanRequestValidator validator;
    private final LoanRequestEventCreator loanRequestEventCreator;

    // we are not handling all cases of the real world application
    public LoanRequest create(CreateLoanRequest request) {
        validator.validate(request);
        var entity = repository.save(mapper.toEntity(request));
        loanRequestEventCreator.create(entity.getId());
        return mapper.map(entity);
    }

    public LoanRequest getLoanRequest(Long id) {
        return repository.findById(id).map(mapper::map).orElseThrow(() -> LoanRequestNotFoundException.of(id));
    }
}
