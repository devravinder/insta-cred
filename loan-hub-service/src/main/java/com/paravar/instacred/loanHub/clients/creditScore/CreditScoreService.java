package com.paravar.instacred.loanHub.clients.creditScore;

import com.paravar.instacred.common.domain.models.CreditScore;
import com.paravar.instacred.common.domain.models.CreditScoreNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class CreditScoreService {
    private final WebClient creditScoreClient;

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
}
