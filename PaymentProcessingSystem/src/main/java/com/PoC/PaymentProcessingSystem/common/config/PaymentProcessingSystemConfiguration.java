package com.PoC.PaymentProcessingSystem.common.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PaymentProcessingSystemConfiguration {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder, @Value("${broker.url}") String url) {
        return builder.rootUri(url).build();
    }
}
