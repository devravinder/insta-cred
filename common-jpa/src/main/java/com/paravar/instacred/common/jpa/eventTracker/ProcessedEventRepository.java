package com.paravar.instacred.common.jpa.eventTracker;

import com.paravar.instacred.common.jpa.models.Event;
import com.paravar.instacred.common.jpa.models.ProcessedEvent;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessedEventRepository extends JpaRepository<ProcessedEvent, String> {
    @Query(
            """
        select new com.paravar.instacred.common.jpa.models.Event(o.eventId, o.eventName)
        from ProcessedEvent o
        where o.eventId = :eventId and o.eventName = :eventName
        """)
    Optional<Event> findByEventId(String eventId, String eventName);
}
