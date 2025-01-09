package com.paravar.instacred.common.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@ComponentScan(basePackageClasses = JpaAutoConfiguration.class)
@EnableJpaRepositories(basePackageClasses = JpaAutoConfiguration.class)
@EntityScan(basePackageClasses = JpaAutoConfiguration.class)
public class JpaAutoConfiguration {
}
