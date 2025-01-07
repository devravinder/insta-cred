package com.paravar.instacred.loanProcess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class LoanProcessService {
    public static void main(String[] args) {
        SpringApplication.run(LoanProcessService.class, args);
    }
}
