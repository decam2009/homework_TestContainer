package com.example.springboot_conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {
    private final static String PROFILE = "netology.profile.dev";

    @Bean
    @ConditionalOnProperty(name = PROFILE, havingValue = "true")
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(name = PROFILE, havingValue = "false")
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
