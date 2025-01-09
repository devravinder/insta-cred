package com.paravar.instacred.loanHub.domain.loanRequest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface LoanRequestRepository extends JpaRepository<LoanRequestEntity, Long> {}
