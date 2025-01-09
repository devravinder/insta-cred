package com.paravar.instacred.loanProcess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ConfigurationPropertiesScan
@EntityScan(basePackageClasses = LoanProcessService.class)
@EnableJpaRepositories(basePackageClasses = LoanProcessService.class)
public class LoanProcessService {
    public static void main(String[] args) {
        SpringApplication.run(LoanProcessService.class, args);
    }
}
