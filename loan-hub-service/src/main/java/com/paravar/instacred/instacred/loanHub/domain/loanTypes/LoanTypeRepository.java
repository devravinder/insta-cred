package com.paravar.instacred.instacred.loanHub.domain.loanTypes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface LoanTypeRepository extends JpaRepository<LoanTypeEntity, Long> {
    boolean existsByName(String name);
}
