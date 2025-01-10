package com.paravar.instacred.creditScore.data.migrations;

import com.mongodb.client.MongoDatabase;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;

@ChangeUnit(id = "add-processed-events", order = "003", author = "Ravinder Reddy Kothabad")
public class Cu3AddProcessedEvents {

    @Execution
    public void addProcessedEvents(MongoDatabase mongoDatabase) {
        mongoDatabase.createCollection("processed_events");
    }

    @RollbackExecution
    public void deleteCreditScores(MongoDatabase mongoDatabase) {
        mongoDatabase.getCollection("processed_events").drop();
    }
}
