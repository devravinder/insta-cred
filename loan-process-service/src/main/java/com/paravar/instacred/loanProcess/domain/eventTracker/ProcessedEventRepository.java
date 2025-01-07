package com.paravar.instacred.loanProcess.domain.eventTracker;

import com.paravar.instacred.loanProcess.domain.models.ProcessedEvent;
import org.springframework.data.jpa.repository.JpaRepository;

interface ProcessedEventRepository extends JpaRepository<ProcessedEvent, String> {}
