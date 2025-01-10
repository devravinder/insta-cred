package com.paravar.instacred.creditScore.domain.eventTracker;

import com.paravar.instacred.creditScore.domain.models.ProcessedEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

interface ProcessedEventRepository extends MongoRepository<ProcessedEvent, String> {}
