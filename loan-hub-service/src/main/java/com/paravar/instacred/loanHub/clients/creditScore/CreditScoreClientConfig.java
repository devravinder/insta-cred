package com.paravar.instacred.loanHub.clients.creditScore;

import com.paravar.instacred.loanHub.ApplicationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class CreditScoreClientConfig {

    private final ApplicationProperties properties;

    @Bean
    public WebClient creditScoreClient(WebClient.Builder builder) {
        return builder.baseUrl(properties.credScoreServiceUrl()).build();
    }
}
