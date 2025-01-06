package com.paravar.instacred.loanHub.clients.creditScore;

import com.paravar.instacred.loanHub.ApplicationProperties;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.client.reactive.ReactorNetty2ClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
@RequiredArgsConstructor
public class CreditScoreClientConfig {

    private final ApplicationProperties properties;

    @Bean
    public WebClient creditScoreClient(WebClient.Builder builder) {

        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000) // to establish the connection
                .responseTimeout(Duration.ofSeconds(5)) // maximum time to wait for the first byte of a response
                .doOnConnected(conn -> conn
                        .addHandlerLast(new ReadTimeoutHandler(5)) // max time to wait...while reading the response
                        .addHandlerLast(new WriteTimeoutHandler(5))); // max time to wait while sending data to the server

        ReactorClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);


        builder.clientConnector(connector);
        return builder.baseUrl(properties.credScoreServiceUrl()).build();
    }
}
