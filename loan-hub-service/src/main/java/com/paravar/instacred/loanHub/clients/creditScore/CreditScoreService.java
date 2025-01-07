package com.paravar.instacred.loanHub.clients.creditScore;

import com.paravar.instacred.common.domain.models.CreditScore;
import com.paravar.instacred.common.domain.models.CreditScoreNotFoundException;
import com.paravar.instacred.common.domain.models.ServiceDownException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
@Slf4j
public class CreditScoreService {
    private final WebClient creditScoreClient;

    @CircuitBreaker(name = "credit-score-service")
    @Retry(name = "credit-score-service", fallbackMethod = "geCreditScoreFallback")
    public CreditScore getCreditScore(String panNo) {
        return creditScoreClient
                .get()
                .uri("/creditScores/{panNo}", panNo)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    if (response.statusCode().equals(HttpStatus.NOT_FOUND)) {
                        return Mono.error(CreditScoreNotFoundException.of(panNo));
                    }
                    return Mono.error(new RuntimeException("Server error: " + response.statusCode()));
                })
                .bodyToMono(CreditScore.class)
                .block();
    }

    // this will be called only after 2 retries ( for non ignored exceptions )
    public CreditScore geCreditScoreFallback(String panNo, Throwable t) {
        // handle ignored exceptions
        // for ignored exceptions, fallback will be called without retries/circuit breaker
        if (t instanceof CreditScoreNotFoundException ex) {
            throw ex;
        }

        // handle non ignored exceptions

        log.info("credit score fallback: panNo:{}, Error: {} ", panNo, t.getMessage());
        throw ServiceDownException.of("Credit score service");
    }
}
