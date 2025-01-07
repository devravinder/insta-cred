package com.paravar.instacred.loanHub;

import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableCaching
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "2m")
public class LoanHubApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoanHubApplication.class, args);
    }
}
