package com.paravar.instacred.loanProcess.domain.eventTracker;

import com.paravar.instacred.loanProcess.domain.models.EventId;
import com.paravar.instacred.loanProcess.domain.models.ProcessedEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

interface ProcessedEventRepository extends JpaRepository<ProcessedEvent, String> {
    @Query(
            """
        select new com.paravar.instacred.loanProcess.domain.models.EventId(o.eventId)
        from LoanRequestEvent o
        where o.eventId = :eventId
        """)
    Optional<EventId> findByEventId(String eventId);

}
