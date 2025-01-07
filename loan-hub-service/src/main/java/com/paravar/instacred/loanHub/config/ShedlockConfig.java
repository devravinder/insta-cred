package com.paravar.instacred.loanHub.config;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.redis.spring.RedisLockProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
class ShedlockConfig {
    @Bean
    public LockProvider lockProvider(LettuceConnectionFactory connectionFactory) {
        return new RedisLockProvider(connectionFactory);
    }
}
