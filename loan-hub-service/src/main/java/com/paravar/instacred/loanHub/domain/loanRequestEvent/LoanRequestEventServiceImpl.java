package com.paravar.instacred.loanHub.domain.loanRequestEvent;

import com.paravar.instacred.loanHub.domain.LoanRequestEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
class LoanRequestEventServiceImpl implements LoanRequestEventService {
    private final LoanRequestEventRepository repository;

    public void create(Long loanRequestId) {
        log.info("saving loan request event entity");
        LoanRequestEventEntity entity = LoanRequestEventEntity.builder().loanRequestId(loanRequestId).build();
        repository.save(entity);
    }

    public void delete(Long id) {
        log.info("deleting loan request event entity");
        repository.deleteById(id);
    }

}