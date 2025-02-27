package com.paravar.instacred.creditScore.domain.creditScore;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

interface CreditScoreRepository extends MongoRepository<CreditScoreEntity, String> {

    Optional<CreditScoreEntity> findByPanNo(String panNo);
}
