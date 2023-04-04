package com.middleware.libertymutualmiddlewarechallenge.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.middleware.libertymutualmiddlewarechallenge.jpa")
@Configuration
public class DatabaseConfiguration {
}