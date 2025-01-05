package com.paravar.instacred.creditScore;

import io.mongock.runner.springboot.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableMongock
public class CreditScoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(CreditScoreApplication.class, args);
    }
}
