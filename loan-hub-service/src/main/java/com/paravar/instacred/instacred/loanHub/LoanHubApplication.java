package com.paravar.instacred.instacred.loanHub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class LoanHubApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoanHubApplication.class, args);
    }
}
