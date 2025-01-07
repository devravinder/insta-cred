package com.paravar.instacred.loanHub.domain.loanRequestEvent;

import com.paravar.instacred.loanHub.domain.models.LoanRequestEvent;
import org.springframework.data.jpa.repository.JpaRepository;

interface LoanRequestEventRepository extends JpaRepository<LoanRequestEvent, String> {}
