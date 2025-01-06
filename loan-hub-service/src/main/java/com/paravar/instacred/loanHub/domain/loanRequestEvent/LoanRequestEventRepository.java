package com.paravar.instacred.loanHub.domain.loanRequestEvent;

import org.springframework.data.jpa.repository.JpaRepository;

 interface LoanRequestEventRepository extends JpaRepository<LoanRequestEventEntity, Long> {
}