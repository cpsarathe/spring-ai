package com.cp.openai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.DefaultResponseErrorHandler;

@Configuration
public class OpenAiConfig {
    @Bean
    public ResponseErrorHandler responseErrorHandler() {
        return new DefaultResponseErrorHandler();
    }
}

