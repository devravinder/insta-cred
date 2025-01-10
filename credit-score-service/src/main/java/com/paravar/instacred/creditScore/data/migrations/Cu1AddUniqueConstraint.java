package com.paravar.instacred.creditScore.data.migrations;

import com.paravar.instacred.creditScore.domain.creditScore.CreditScoreEntity;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;

// Cu = Change Unit
@ChangeUnit(id = "add-unique-panNo", order = "001", author = "Ravinder Reddy Kothabad")
@RequiredArgsConstructor
public class Cu1AddUniqueConstraint {
    private final MongoTemplate mongoTemplate;

    @Execution
    public void addUniqueConstraint() {
        mongoTemplate
                .indexOps(CreditScoreEntity.class)
                .ensureIndex(new Index().on("panNo", Sort.Direction.ASC).unique());
    }

    @RollbackExecution
    public void rollback() {
        // Logic to drop the unique index if needed
        mongoTemplate.indexOps(CreditScoreEntity.class).dropIndex("panNo_1");
    }
}
