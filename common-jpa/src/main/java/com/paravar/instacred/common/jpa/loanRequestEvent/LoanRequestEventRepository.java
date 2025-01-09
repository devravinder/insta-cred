package com.paravar.instacred.common.jpa.loanRequestEvent;

import com.paravar.instacred.common.jpa.models.LoanRequestEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRequestEventRepository extends JpaRepository<LoanRequestEvent, String> {
}
